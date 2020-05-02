package com.cyl.flashsaleorder.service;

import com.cyl.flashsalestock.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    boolean create_order (String order_id,String user_id,String shop_id);

    List orderlist(String User_id) ;

    Map findorderbyid (String order_id);
}
