package com.cyl.flashsaleorder.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.cyl.flashsaleorder.dao.OrderDao;
import com.cyl.flashsalestock.entity.Order;
import com.cyl.flashsalestock.entity.ShopDetail;
import com.cyl.flashsalestock.entity.StockLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OrderConsumer {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = "queue")
    public void getmessage (String message) {
        System.out.println("消费信息："+message);
    }

    @RabbitListener(queues = "stockqueue")
    public void getsotckmessage (String message) {
        System.out.println("订单号为："+message);
        //消息为订单号
        //通过订单号获取商品号
        Order order = orderDao.findorderbyid(message);
        if (order!=null) {
            //减少商品库存
            StockLog stockLog = new StockLog();
            stockLog.setShop_id(order.getShop_id());
            stockLog.setIncr(0);
            stockLog.setDecr(1);
            Map map = restTemplate.postForObject("http://STOCK/updatestock", stockLog, Map.class);
            boolean res = (boolean) map.get("result");
            if (res) {
                //去库存成功
                System.out.println("自动扣减库存了");
            }
        }


    }

    @RabbitListener(queues = "orderqueue")
    public void getmapmessage (Message message) {
        Order order= (Order) SerializationUtils.deserialize(message.getBody());
        //从数据库中查询商品折扣价
        try {
            Map map = restTemplate.getForObject("http://STOCK/stock/" + order.getShop_id(), Map.class);
            List data = (List) map.get("data");
            String detailstring = JSON.toJSONString(data.get(0));
            ShopDetail shopDetail = JSON.parseObject(detailstring,ShopDetail.class);
            if (shopDetail!=null) {
                order.setMoney(shopDetail.getShop().getDiscount().getDiscount()) ;
                System.out.println("消费信息："+order);
                orderDao.create_order(order);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

}
