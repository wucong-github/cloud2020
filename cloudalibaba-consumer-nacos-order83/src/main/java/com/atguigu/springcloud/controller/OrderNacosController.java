package com.atguigu.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 使用83端口，通过 REST风格 调用9001和9002端口
 * 注入RestTemplate ,使用服务名调用9001和9002端口
 * Nacos 自带负载均衡
 */
@RestController
@RequestMapping("/consumer")
public class OrderNacosController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/nacos/{id}")
    public String paymentInfo(@PathVariable Integer id) {

        // 使用nacos
        System.out.println("nacos的 id 是" + id);
        System.out.println("nacos前缀 :" + serverURL);
        //
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);

    }
}
