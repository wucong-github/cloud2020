package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "fallbackDefault")
public class OrderController {


    @Resource
    private PaymentHystrixService service;


    @GetMapping("/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        int i = 10 / 0;
        return service.paymentInfo_OK(id);
    }

    @GetMapping("/hystrix/out/{id}")
    @HystrixCommand(fallbackMethod = "paument_Global_FallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })

    public String paymenInfo_TimeOut(@PathVariable("id") Integer id) {

        return service.paymenInfo_TimeOut(id);
    }


    // 80端口的服务降级方法，
    public String paument_Global_FallbackMethod(@PathVariable("id") Integer id) {

        return "消费者80 出错   Global系统繁忙 ，请稍后再试 ";
    }

    // 80端口的默认服务降级方法，
    public String fallbackDefault() {

        return "消费者80端口 默认降级方法   出错Global系统繁忙GlobalGlobalGlobal，请稍后再试 ";
    }

}









