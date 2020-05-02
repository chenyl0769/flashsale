package com.cyl.flashsalestock.service;

import com.cyl.flashsalestock.entity.Discount;
import com.cyl.flashsalestock.entity.Shop;
import com.cyl.flashsalestock.entity.ShopDetail;
import com.cyl.flashsalestock.entity.StockLog;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface StockService {
    Map<String, Object> getAll();

    Map<String, Object> getShopDetail(String shop_id);

    boolean adddiscount(Discount discount);

    Map addorder (String shop_id, HttpSession session);

    Map orderlist(String user_id);

    Map orderbyid(String order_id);

    Map pay(String order_id,double money);

    Map stockupdate (StockLog stockLog);

    Map addshops(Map map);
}
