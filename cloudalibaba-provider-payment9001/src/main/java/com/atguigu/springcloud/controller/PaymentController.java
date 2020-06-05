package com.atguigu.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 进行9001 和9002 是服务的注册， nacos注册中心的客户端
 */
@RestController
@RequestMapping("/payment")

public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    // 需要对9001进行验证，进行服务的提供
    @GetMapping(value = "/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        // 使用9001端口和9002端口，通过端口号证明它是可行的
        System.out.println("Hello Nacos: " + serverPort + "\t id: " + id);
        //
        return "Hello Nacos Discovery: " + serverPort + "\t id: " + id;
    }


}


