package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient client;

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

    @GetMapping("/lb")
    public String getpaumentLB() {

        // 从 Eureka 获取名称为 "CLOUD-PAYMENT-SERVICE" 的服务实例集合
        List<ServiceInstance> srvList = client.getInstances("CLOUD-PAYMENT-SERVICE");
        // 判空
        if (null == srvList || srvList.size() <= 0) {
            return null;
        }

        /*
         * 将服务器实例集合传入instance（）
         * 通过自定义算法，
         * 会返回当前集合中的一个服务器实例
         * 然后用这个服务器实例的信息（地址和端口号），
         * 构建访问地址，去访问这台服务器
         */
        ServiceInstance instance = loadBalancer.instance(srvList);
        URI uri = instance.getUri();
        System.out.println(uri.toString());
        // 轮询 访问
        return restTemplate.getForObject(uri + "/payment/lb", String.class);

    }

    /**
     * 80端口是服务的消费端，使用链路追踪，显示80端口的服务调用踪迹
     *
     * @return
     */
    @GetMapping("/zipkin")
    public String paymentZipkin() {
        //使用RestTemplate 要在config包中注入RestTemplate
        //使用 @LoadBalanced标签进行负载均衡后，不能使用端口号访问，统一使用微服务名访问
        // 先使用seleuth 可以使用spring进行追踪，可以使用
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
    }


    @GetMapping("/zipkin2")
    public String paymentZipkin2() {

        return restTemplate.getForObject("http://CLOUD-STREAM-PROVIDER/zipkin2", String.class);

    }

}









