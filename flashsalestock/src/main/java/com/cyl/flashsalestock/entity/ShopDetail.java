package com.cyl.flashsalestock.entity;

import java.io.Serializable;

/***
 * 商品详细实体类
 */
public class ShopDetail implements Serializable {

    private String shop_id;
    private String description;
    private Shop shop;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ShopDetail{" +
                "shop_id='" + shop_id + '\'' +
                ", description='" + description + '\'' +
                ", shop=" + shop +
                '}';
    }
}
