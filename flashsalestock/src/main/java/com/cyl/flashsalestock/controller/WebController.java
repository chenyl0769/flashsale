package com.cyl.flashsalestock.controller;

import com.cyl.flashsalestock.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {
    //跳转到创建活动的页面
    @RequestMapping("/discount")
    public String adddiscount() {
        return "discount";
    }
    //跳转到初始页面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //跳转到商品详细页
    @RequestMapping("/detail/{shop_id}")
    public String detail(@PathVariable("shop_id") String shop_id) {
        return "detail";
    }

    //跳转登录页面
    @RequestMapping("/login")
    public String login () {
        return "login";
    }

    //跳转注册页面
    @RequestMapping("/regist")
    public String reg () {
        return "reg";
    }

    //跳转用户主页
    @RequestMapping("/main")
    public String main (HttpSession session) {
        User user= (User)session.getAttribute("user");
        if (user==null) {
            return "login";
        }
        return "main";
    }

    //跳转到支付页面
    @RequestMapping("/pay/{order_id}")
    public String pay(@PathVariable("order_id") String order_id) {
        return "pay";
    }

    //跳转到管理页面
    @RequestMapping("/admin")
    public String admin () {
        return "admin";
    }
}
