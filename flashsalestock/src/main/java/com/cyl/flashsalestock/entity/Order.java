package com.cyl.flashsalestock.entity;

import java.io.Serializable;
import java.util.Date;

/***
 * 订单实体类
 */
public class Order implements Serializable {
    private String order_id;
    private String shop_id;
    private String user_id;
    private Date create_time;
    private double money;
    private Integer statu;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer status) {
        this.statu = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", create_time=" + create_time +
                ", money=" + money +
                ", statu=" + statu +
                '}';
    }
}
