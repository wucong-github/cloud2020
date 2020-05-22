package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * 设置消息消费者的集群，消费者解析消息，需要集群分组
 * 避免消息重复消费。
 * 需要设置的是yml文件，去设置分组
 * 先进行yml配置，   group:  atguigu8
 * 分组名相同时 消息只会被消费一次什么
 */
@SpringBootApplication
public class MessageMain8803 {

     public static void main(String[] args) {
        SpringApplication.run(MessageMain8803.class,args);
    }

}
