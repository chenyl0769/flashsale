package com.cyl.flashsaleorder.dao;

import com.cyl.flashsaleorder.mapper.OrderMapper;
import com.cyl.flashsalestock.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImp implements OrderDao {
    @Autowired
    private OrderMapper orderMapper;
    //创建订单
    @Override
    public Integer create_order(Order order) {
        return orderMapper.addorder(order);
    }

    //根据用户id查询订单列表
    @Override
    public List orderlist(String user_id) {
        return orderMapper.findorder(user_id);
    }

    //根据订单号获取订单详细
    @Override
    public Order findorderbyid(String order_id) {
        return orderMapper.findorderbyid(order_id);
    }
}
