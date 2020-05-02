package com.cyl.flashsalestock.service;

import com.cyl.flashsalestock.dao.StockDao;
import com.cyl.flashsalestock.entity.*;
import com.cyl.flashsalestock.util.MyResults;
import com.cyl.flashsalestock.util.RedisIncr;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class StockServiceImp implements StockService {
    @Autowired
    private StockDao stockDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisIncr redisIncr;

    /***
     * 获取商品列表
     * @return
     */
    public Map<String, Object> getAll() {
        //获取商品列表
        List<Shop> listshop = stockDao.getAll();
        if (listshop==null||listshop.isEmpty()) {
            //没有找到数据
            return MyResults.getresult(false,"找不到商品",null);
        }
        try {
            //通过获取的列表遍历redis的商品活动信息
            for (Shop shop : listshop) {
                String discount_key = "shopdiscount:" + shop.getShop_id();
                Discount discount = (Discount) redisTemplate.opsForValue().get(discount_key);
                if (discount != null) {
                    shop.setDiscount(discount);
                }
            }
        }catch (Exception e) {
            return MyResults.getresult(false,"出错了！",null);
        }
        return MyResults.getresult(true,"",listshop);
    }

    /***
     * 根据商品ID获取商品详细
     * @param shop_id 商品ID
     * @return
     */
    public Map<String, Object> getShopDetail(String shop_id) {
        List details= stockDao.getShopDetail(shop_id);
        if (details==null||details.isEmpty()) {
            return MyResults.getresult(false,"找不到商品",null);
        }
        return MyResults.getresult(true,"",details);
    }

    /***
     * 创建活动
     * @param discount 活动信息
     * @return 处理结果
     */
    @Override
    @Transactional
    public boolean adddiscount(Discount discount) {
        //秒杀活动的I D
        String dis_id= UUID.randomUUID().toString();
        discount.setDis_id(dis_id);
        //插入mysql
        if (stockDao.adddiscount(discount) == true) {
            //插入redis
            int dis = 0;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //计算活动在redis的生存时间
            try {
                Date nowtime = simpleDateFormat.parse(restTemplate.getForObject("http://TIMESERVICE/gettime", String.class));
                Date endtime = discount.getEndtime();
                dis = (int) ((endtime.getTime() - nowtime.getTime()) / 1000);
                String discount_key = "shopdiscount:" + discount.getShop_id();
                redisTemplate.opsForValue().set(discount_key, discount, dis, TimeUnit.SECONDS);
                return true;
            } catch (ParseException e) {
                //插入redis失败，回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

        }

        return false;
    }

    /***
     * 创建订单
     * @param shop_id 商品ID
     * @param session httpsession对象用于获取用户信息
     * @return 处理结果
     */
    @Override
    @Transactional
    public Map addorder(String shop_id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //是否登录
        if (user==null) {
            //未登录
            return MyResults.getresult(false,"未登录",null);
        }
        //是否有优惠
        //优惠政策的redis键
        String discount_key = "shopdiscount:" + shop_id;
        Discount discount = (Discount) redisTemplate.opsForValue().get(discount_key);
        if (discount==null) {
            //没有优惠政策
            return MyResults.getresult(false,"没有优惠",null);
        }
        //是否在优惠时间内
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long n_time = simpleDateFormat.parse(restTemplate.getForObject("http://TIMESERVICE/gettime", String.class)).getTime();
            Long t_start =discount.getStarttime().getTime();
            Long t_end = discount.getEndtime().getTime();

            if (n_time<t_start||n_time>t_end) {
                //不在活动时间内
                return MyResults.getresult(false,"不在活动期内",null);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //resis计数器
        int stock= discount.getStock();
        //redis中秒杀自增的key
        String incrkey =discount.getDis_id();
        //计数器自增
        Long getincr = redisIncr.getincr(incrkey, 0);
        if (getincr>=stock) {
            //超出库存设置数量，卖完
            return MyResults.getresult(false,"手慢了",null);
        }
        //消息队列
        //创建订单
        try {
            //调用订单服务
            String order_id =restTemplate.getForObject("http://ORDER/addorder/"+shop_id+"/"+user.getUser_Id(), String.class);

            if (order_id!=null&&order_id!="") {
                //抢到了
                return MyResults.getresult(true,"抢到了",order_id);
            }
        }catch (Exception e) {
            //计数器自减，回滚
            redisIncr.getdecr(incrkey, 0);
        }
        return MyResults.getresult(false,"出错了",null);


    }

    /***
     * 根据用户ID查询订单信息
     * @param user_id 用户ID
     * @return 订单信息的map
     */
    @Override
    public Map orderlist(String user_id) {
        try {
            //调用订单服务
            List orderlist = restTemplate.getForObject("http://ORDER/orderlist/" + user_id, List.class);
            if (orderlist!=null&&!orderlist.isEmpty()) {
                return MyResults.getresult(true,"",orderlist);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return MyResults.getresult(false,"还不买点东西啊",null);
    }

    /***
     * 根据订单ID查询订单信息
     * @param order_id
     * @return
     */
    @Override
    public Map orderbyid(String order_id) {
        //订单服务
        Map map = restTemplate.getForObject("http://ORDER/orderbyid/" + order_id, Map.class);
        return map;
    }

    /***
     * 订单支付
     * @param order_id 订单ID
     * @param money 订单金额
     * @return 支付结果
     */
    @Override
    public Map pay(String order_id,double money) {
        //支付平台支付获取返回值,现直接支付成功。更新订单为已经支付
        int res = stockDao.updateorderstatu(order_id, 1);
        if (res>0) {
            //成功更新订单为已支付
            //通知队列减库存
            rabbitTemplate.convertAndSend("stockex","order",order_id);
            //返回结果
            return MyResults.getresult(true,"准备出库",null);
        }
        return MyResults.getresult(false,"支付失败",null);
    }

    /***
     * 库存更新
     * @param stockLog 库存记录
     * @return 处理结果
     */
    @Override
    @Transactional
    public Map stockupdate(StockLog stockLog) {
        //插入库存记录
        int resultlie = stockDao.addstocklog(stockLog);
        if (resultlie>0) {
            //实际变化数量
            int addstock =stockLog.getIncr()-stockLog.getDecr();
            //查询现有数量
            Shop shop = stockDao.shopbyid(stockLog.getShop_id());
            int stocknow= shop.getStock();
            int newstock = stocknow+addstock;
            shop.setStock(newstock);
            int i = stockDao.stockupdate(shop);
            if (i>0) {
                return  MyResults.getresult(true,"更新成功",null);
            }

        }
        return MyResults.getresult(false,"更新失败",null);
    }

    /***
     * 增加商品
     * @param map 用map来封装商品信息键值对
     * @return
     */
    @Override
    @Transactional
    public Map addshops(Map map) {
        Shop shop = new Shop();
        ShopDetail shopDetail = new ShopDetail();
        //设置商品信息
        String shop_id = UUID.randomUUID().toString();
        shop.setShop_id(shop_id);
        shop.setStock(Integer.valueOf((String) map.get("stock")) );
        shop.setImage((String) map.get("image"));
        shop.setPrice(Double.valueOf((String) map.get("price")));
        shop.setTitle((String) map.get("title"));
        String description= (String) map.get("description");
        //增加商品
        int addshop = stockDao.addshop(shop);
        if (addshop>0&&description!=null&&description!="") {
            //增加商品详细
            shopDetail.setShop_id(shop_id);
            shopDetail.setDescription(description);
            int addshopdetail = stockDao.addshopdetail(shopDetail);
            if (addshopdetail>0) {
                return MyResults.getresult(true,"添加成功",null);
            }
        }
        return MyResults.getresult(false,"添加失败",null);
    }


}
