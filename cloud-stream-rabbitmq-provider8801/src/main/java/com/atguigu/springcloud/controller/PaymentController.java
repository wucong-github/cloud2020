package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class PaymentController {
    @Autowired
    private MessageProvider provider;

    @GetMapping("/sendMessage")
    public String sendmessage(){
        System.out.println("sendmessage.... @GetMapping");
        provider.send();
        return "8801端口，发送消息";
    }



}
