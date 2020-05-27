package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  @EnableEurekaClient 开启erueka 客户端
 *
 *  @EnableDiscoveryClient 开启服务发现
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class PaymentMain8001 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8001.class,args);

    }

}
