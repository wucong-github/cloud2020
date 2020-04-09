package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @EnableEurekaClient 开启erueka 客户端
 *
 *  @EnableFeignClients 开启Feign 服务负载均衡
 */
@EnableFeignClients(basePackages="com.atguigu.springcloud")
@EnableEurekaClient
@SpringBootApplication
public class OrderFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }

}
