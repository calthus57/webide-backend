package com.ustc.webide.ideproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


//注册到注册中心（发现中心）
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class IdeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeProjectApplication.class, args);
    }

}
