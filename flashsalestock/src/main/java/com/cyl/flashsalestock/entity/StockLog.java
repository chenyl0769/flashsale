package com.cyl.flashsalestock.entity;

import java.io.Serializable;

/***
 * 商品记录实体类
 */
public class StockLog implements Serializable {

    private String shop_id;
    private int incr;
    private int decr;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public int getIncr() {
        return incr;
    }

    @Override
    public String toString() {
        return "StockLog{" +
                "shop_id='" + shop_id + '\'' +
                ", incr=" + incr +
                ", decr=" + decr +
                '}';
    }

    public void setIncr(int incr) {
        this.incr = incr;
    }

    public int getDecr() {
        return decr;
    }

    public void setDecr(int decr) {
        this.decr = decr;
    }
}
