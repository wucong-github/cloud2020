package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create")
    public CommonResult<Payment> creart(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

    }


    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    // 测试@EnableDiscoveryClient ,消费端可以调用服务发现
    @GetMapping("/discovery")
    public Object discovery() {

        return restTemplate.getForObject(PAYMENT_URL + "/payment/discovery", Object.class);
    }


    // 测试getForEntity 返回一个ResponseEntity对象
    @GetMapping("/get2/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode().toString());
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }


    // 测试postForEntity 返回一个ResponseEntity对象   serial
    @GetMapping("/create2")
    public CommonResult<Payment> creart2(Payment payment) {

        ResponseEntity<CommonResult> entity =
                restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

        log.info(entity.getStatusCode().toString());
        return entity.getBody();
    }


}









