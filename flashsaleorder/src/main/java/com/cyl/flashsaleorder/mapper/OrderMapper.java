package com.cyl.flashsaleorder.mapper;

import com.cyl.flashsalestock.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    //创建订单
    @Insert("insert into orders (order_id,shop_id,user_id,create_time,money,statu)" +
            "values(#{order.order_id},#{order.shop_id},#{order.user_id},#{order.create_time},#{order.money},#{order.statu})")
    int addorder (@Param("order") Order order);


    //查询用户订单
    @Select("select order_id,shop_id,create_time,money,statu from orders where user_id = #{user_id}")
    @Results({
            @Result(property = "order_id",column = "order_id",javaType = String.class),
            @Result(property = "shop_id",column = "shop_id",javaType = String.class),
            @Result(property = "create_time",column = "create_time",javaType = Date.class),
            @Result(property = "money",column = "money",javaType = double.class),
            @Result(property = "statu",column = "statu",javaType = Integer.class),
    })
    List<Order> findorder (@Param("user_id")String user_id);


    //根据订单号查询订单
    @Select("select order_id,shop_id,create_time,money,statu from orders where order_id = #{order_id}")
    @Results({
            @Result(property = "order_id",column = "order_id",javaType = String.class),
            @Result(property = "shop_id",column = "shop_id",javaType = String.class),
            @Result(property = "create_time",column = "create_time",javaType = Date.class),
            @Result(property = "money",column = "money",javaType = double.class),
            @Result(property = "statu",column = "statu",javaType = Integer.class),
    })
    Order findorderbyid (@Param("order_id")String order_id);
}
