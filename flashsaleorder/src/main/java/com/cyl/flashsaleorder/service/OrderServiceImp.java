package com.cyl.flashsaleorder.service;

import com.cyl.flashsaleorder.dao.OrderDao;
import com.cyl.flashsalestock.entity.Order;
import com.cyl.flashsalestock.util.MyResults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderDao orderDao;

    /***
     * 建立订单
     * @param order_id 订单号
     * @param user_id 用户ID
     * @param shop_id 商品ID
     * @return
     */
    @Override
    public boolean create_order(String order_id, String user_id,String shop_id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date nowtime = null;
        try {
            //获取当前时间
            nowtime = simpleDateFormat.parse(restTemplate.getForObject("http://TIMESERVICE/gettime", String.class));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //创建订单对象并赋值
        Order order = new Order();
        order.setOrder_id(order_id);
        order.setShop_id(shop_id);
        order.setStatu(0);
        order.setUser_id(user_id);
        order.setCreate_time(nowtime);
        //发送订单队列，立即返回结果
        rabbitTemplate.convertAndSend("flashsale","order",order);
        return true;
    }

    /***
     * 查询用户订单
     * @param User_id 用户ID
     * @return 订单列表
     */
    @Override
    public List orderlist(String User_id) {
        //返回列表
        return orderDao.orderlist(User_id);
    }


    /**
     * 根据订单号查询订单信息
     * @param order_id 订单号
     * @return 订单详细
     */
    @Override
    public Map findorderbyid(String order_id) {
        Order order = orderDao.findorderbyid(order_id);
        if (order==null) {
            return MyResults.getresult(false,"没有找到订单",null);
        }
        return MyResults.getresult(true,"",order);
    }
}
