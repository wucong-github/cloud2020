package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping
public class PaymentController {


    @Autowired
    private MessageChannel output;

    @GetMapping("/sendMessage")
    public String sendmessage(){
        System.out.println("sendmessage.... @GetMapping");
        String s = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        return "8801端口，发送消息";
    }


    @GetMapping("/zipkin2")
    public String zipkin2(){

        return "8801端口，发送消息，发送到";
    }

}
