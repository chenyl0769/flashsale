package com.cyl.flashsaletimeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class FlashsaletimeserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashsaletimeserverApplication.class, args);
    }

}
