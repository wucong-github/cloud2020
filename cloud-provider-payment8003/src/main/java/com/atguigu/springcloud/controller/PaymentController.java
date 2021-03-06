package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private String port;

    @PostMapping("/create")
    public CommonResult creart(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);
        System.out.println("12334");
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功" + port, result);
        } else {
            return new CommonResult(404, "插入数据库失败" + port, null);
        }
    }


    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        System.out.println(id);

        log.info("****查询结果：" + paymentById);

        if (null != paymentById) {
            return new CommonResult(200, "查询数据库成功8003", paymentById);
        } else {
            return new CommonResult(404, "查询数据库失败8003", null);
        }
    }


    @GetMapping("/discovery")
    public Object discovery() {

        // getServices() 取出注册中心的所有服务名称
        List<String> list = client.getServices();
        list.forEach(System.out::println);

        // 获取一个名称为 "CLOUD-PAYMENT-SERVICE" 的服务实例
        List<ServiceInstance> srvList = client.getInstances("CLOUD-PAYMENT-SERVICE");
        // 将这个实例的相关信息 依次打印出来
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t"
                    + element.getHost() + "\t"
                    + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;

    }


    @GetMapping("/lb")
    public String getService() {
        //返回端口号
        return port;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return port;
        }
    }

}
