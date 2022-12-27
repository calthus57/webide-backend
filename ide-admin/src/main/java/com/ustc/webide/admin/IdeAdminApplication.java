/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.ustc.webide.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//@EnableFeignClients(basePackages = "com.ustc.webide.admin.module.sys.feign")
public class IdeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeAdminApplication.class, args);
    }

}