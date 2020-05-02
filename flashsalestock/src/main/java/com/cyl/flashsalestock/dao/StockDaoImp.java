package com.cyl.flashsalestock.dao;

import com.cyl.flashsalestock.entity.Discount;
import com.cyl.flashsalestock.entity.Shop;
import com.cyl.flashsalestock.entity.ShopDetail;
import com.cyl.flashsalestock.entity.StockLog;
import com.cyl.flashsalestock.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/***
 * 商品DAO
 */
@Repository
public class StockDaoImp implements StockDao {
    @Autowired
    private ShopMapper shopMapper;
    //获取商品列表
    @Override
    public List<Shop> getAll() {
        return shopMapper.getAll();
    }
    //根据商品ID获取商品信息
    @Override
    public Shop shopbyid(String shop_id) {
        return shopMapper.getShopByShopId(shop_id);
    }
    //根据商品ID获取商品详细
    @Override
    public List<ShopDetail> getShopDetail(String shop_id) {
        return shopMapper.getShopDetailByShopId(shop_id);
    }
    //创建优惠活动
    @Override
    public boolean adddiscount(Discount discount) {


        return shopMapper.adddiscount(discount) == 1;
    }
    //更新商品库存
    @Override
    public int stockupdate(Shop shop) {
        return shopMapper.stockupdate(shop);
    }

    //插入库存变化的记录
    @Override
    public int addstocklog(StockLog stockLog) {
        return shopMapper.addstocklog(stockLog);
    }

    //增加商品信息
    @Override
    public int addshop(Shop shop) {
        return shopMapper.addshop(shop);
    }
    //增加商品详细信息
    @Override
    public int addshopdetail(ShopDetail shopDetail) {
        return shopMapper.addshopdetail(shopDetail);
    }
    //更新订单状态
    @Override
    public int updateorderstatu(String order_id, Integer statu) {
        return shopMapper.orderstatuupdate(order_id,statu);
    }
}
