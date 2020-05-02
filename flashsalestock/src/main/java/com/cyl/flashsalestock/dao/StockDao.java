package com.cyl.flashsalestock.dao;

import com.cyl.flashsalestock.entity.Discount;
import com.cyl.flashsalestock.entity.Shop;
import com.cyl.flashsalestock.entity.ShopDetail;
import com.cyl.flashsalestock.entity.StockLog;

import java.util.List;

public interface StockDao {

    List<Shop> getAll();

    Shop shopbyid(String shop_id);

    List<ShopDetail> getShopDetail(String shop_id);

    boolean adddiscount(Discount discount);

    int stockupdate (Shop shop);

    int addstocklog (StockLog stockLog);

    int addshop(Shop shop);

    int addshopdetail (ShopDetail shopDetail);

    int updateorderstatu (String order_id,Integer statu);
}
