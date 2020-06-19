package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 吴聪
 * @Date 2020/5/11 0011
 * @since 1.0.0
 * nacos 注册管理中心
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3399 {
    // 3399端口配置文件的客户端，真正的服务端是nacos配置中心，
    // 使用liunx 系统进行Nacos的集群，注册中心必须集群
    // 一主二从，修改port 地址 ，有相同的微服务名，且自带负载均衡
    // 和Spring Cloud 进行整合
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3399.class,args);

    }
}

