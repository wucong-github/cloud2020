package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 吴聪
 * @Date 2020/4/8 0008
 * @since 7.1.0
 */

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" , fallback = PaymentFallbackserviceImpl.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/out/{id}")
    String paymenInfo_TimeOut(@PathVariable("id") Integer id);
}
