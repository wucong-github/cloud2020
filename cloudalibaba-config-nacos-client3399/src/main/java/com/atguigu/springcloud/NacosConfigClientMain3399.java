package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 吴聪
 * @Date 2020/5/11 0011
 * @since 1.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3399 {
    // 3399端口配置文件的客户端，真正的服务端是nacos配置中心，
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3399.class,args);

    }
}

