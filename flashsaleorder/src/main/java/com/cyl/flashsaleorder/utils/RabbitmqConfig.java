package com.cyl.flashsaleorder.utils;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/***
 * 队列配置类
 */
@Configuration
public class RabbitmqConfig {
    // 测试队列
    @Bean
    public Queue testqueue () {
        return new Queue("orderqueue",true);
    }
    @Bean
    public Queue stockqueue () {
        return new Queue("stockqueue",true);
    }
    //交换机
    @Bean
    public DirectExchange testexchange () {
        return new DirectExchange("flashsale");
    }
    @Bean
    public DirectExchange stockexchange () {
        return new DirectExchange("stockex");
    }
    //绑定
    @Bean
    public Binding testbing () {
        return BindingBuilder.bind(stockqueue()).to(stockexchange()).with("order");
    }

    @Bean
    public Binding stockbing () {
        return BindingBuilder.bind(testqueue()).to(testexchange()).with("stock");
    }
}
