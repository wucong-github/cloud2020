package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.server.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
@DefaultProperties(defaultFallback = "paument_Global_FallbackMethod")
public class PaymentController {


    @Autowired
    private PaymentService service;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return service.paymentInfo_OK(id);
    }


    @GetMapping("/hystrix/out/{id}")
    public String paymenInfo_TimeOut(@PathVariable("id") Integer id) {
        return service.paymenInfo_TimeOut(id);
    }


    // 下面是全局方法
    public String paument_Global_FallbackMethod(@PathVariable("id") Integer id) {

        return "Global系统繁忙，请稍后再试 ";
    }

    /**
     *========== 服务熔断 ========
     */
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = service.paymentCircuitBreaker(id);
        log.info("*****result: " + result);
        return result;

    }



}
