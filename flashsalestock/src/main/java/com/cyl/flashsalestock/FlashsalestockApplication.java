package com.cyl.flashsalestock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
@MapperScan("com.cyl.flashsalestock.mapper")
public class FlashsalestockApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashsalestockApplication.class, args);
    }

}
