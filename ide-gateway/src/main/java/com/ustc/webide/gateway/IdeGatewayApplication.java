package com.ustc.webide.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.ustc.webide.gateway.feignClient")
@EnableDiscoveryClient
//排除跟数据源有关的配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IdeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeGatewayApplication.class, args);
    }

}
