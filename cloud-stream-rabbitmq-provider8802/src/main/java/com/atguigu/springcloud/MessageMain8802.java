package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  新建了一个8802的复制模块8803，8801端口生产消息，8802 8803端口消费消息
 *
 */
@SpringBootApplication
public class MessageMain8802 {

    public static void main(String[] args) {
        SpringApplication.run(MessageMain8802.class,args);
    }

}