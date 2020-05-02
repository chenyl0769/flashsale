package com.cyl.flashsaleorder.dao;

import com.cyl.flashsalestock.entity.Order;

import java.util.List;

public interface OrderDao {

    Integer create_order (Order order);

    List orderlist (String user_id);

    Order findorderbyid (String order_id);
}
