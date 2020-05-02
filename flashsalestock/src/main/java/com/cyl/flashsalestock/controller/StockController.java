package com.cyl.flashsalestock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyl.flashsalestock.entity.Discount;
import com.cyl.flashsalestock.entity.Order;
import com.cyl.flashsalestock.entity.StockLog;
import com.cyl.flashsalestock.entity.User;
import com.cyl.flashsalestock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private RestTemplate restTemplate;

    //查询所有商品
    @RequestMapping("/stocks")
    public Map stock() {
        return stockService.getAll();
    }

    //通过商品ID查询商品详细
    @RequestMapping("/stock/{shop_id}")
    public Map<String, Object> stock(@PathVariable("shop_id") String shop_id) {
        return stockService.getShopDetail(shop_id);
    }

    //创建商品活动
    @PostMapping("/add")
    public String adddiscount(Discount discount) {
        stockService.adddiscount(discount);
        return "ok";
    }

    //用户登录
    @RequestMapping("/log")
    public Map login(@RequestBody User user,HttpSession session) {
        Map map = restTemplate.postForObject("http://user/log/",user,Map.class);
        String s = JSON.toJSONString(map.get("data"));
        User user1 = JSON.parseObject(s, User.class);
        session.setAttribute("user",user1);
        return map;
    }

    //用户注册
    @RequestMapping("/reg")
    public Map reg(@RequestBody User user) {
        Map map = restTemplate.postForObject("http://user/log/",user,Map.class);
        return map;
    }

    //创建订单
    @RequestMapping("/order/{shop_id}")
    public Map order (@PathVariable("shop_id") String shop_id,HttpSession session) {

        return stockService.addorder(shop_id, session);
    }

    //查询用户的订单信息
    @RequestMapping("/orderlist/{user_id}")
    public Map orderlist (@PathVariable("user_id") String user_id) {

        return stockService.orderlist(user_id);
    }

    //通过订单ID查询订单信息
    @RequestMapping("/orderbyid/{order_id}")
    public Map orderbyid (@PathVariable("order_id") String order_id) {
        return stockService.orderbyid(order_id);
    }

    //支付
    @RequestMapping("/paynow")
    public Map paynow (@RequestBody String data) {
        JSONObject object = (JSONObject) JSONObject.parse(data);
         Order order =JSONObject.parseObject(object.get("data").toString(),Order.class);
        return stockService.pay(order.getOrder_id(),order.getMoney());
    }

    //更新库存
    @RequestMapping("/updatestock")
    public Map updatestock (@RequestBody StockLog stockLog) {
        return stockService.stockupdate(stockLog);
    }

    //增加商品
    @RequestMapping("/addshops")
    public Map addshops (@RequestBody Map<String,Object> map ) {
        return stockService.addshops(map);
    }


}
