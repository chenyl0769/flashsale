package com.cyl.flashsalestock.entity;

import java.io.Serializable;


/***
 * 商品实体类
 */
public class Shop implements Serializable {

    private String shop_id;
    private String title;
    private String image;
    private int stock;
    private double price;
    private Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shop_id='" + shop_id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
