package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author 吴聪
 * @Date 2020/4/17 0017
 * @since 7.1.0
 */
@Component
public class PaymentFallbackserviceImpl implements  PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "@FeignClient标签   paymentInfo_OK 的备用方法";
    }

    @Override
    public String paymenInfo_TimeOut(Integer id) {
        return "@FeignClient标签   paymenInfo_TimeOut 的备用方法";
    }
}
