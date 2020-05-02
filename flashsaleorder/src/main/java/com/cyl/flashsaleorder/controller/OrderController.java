package com.cyl.flashsaleorder.controller;

import com.cyl.flashsaleorder.service.OrderService;
import com.cyl.flashsalestock.util.RedisIncr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /***
     * 创建订单返回订单号
     * @param shop_id 商品ID
     * @param user_id 用户ID
     * @return 订单号
     */
    @RequestMapping("/addorder/{shop_id}/{user_id}")
    public String addorder (@PathVariable("shop_id") String shop_id,@PathVariable("user_id") String user_id) {
        //订单id
        String order_id = UUID.randomUUID().toString();
        //生成订单，订单id，用户id，商品id，时间，金额，状态
        boolean result = orderService.create_order(order_id, user_id,shop_id);
        if (!result) {
            return null;
        }
        return order_id;
    }

    /***
     * 查询用户所有订单
     * @param user_id 用户ID
     * @return 订单列表
     */
    @RequestMapping("/orderlist/{user_id}")
    public List orderlist (@PathVariable("user_id") String user_id) {
        return orderService.orderlist(user_id);
    }

    /***
     * 根据订单号查询订单信息
     * @param order_id 订单号
     * @return
     */
    @RequestMapping("/orderbyid/{order_id}")
    public Map orderbyid(@PathVariable("order_id")String order_id) {

        return orderService.findorderbyid(order_id);
    }
}
