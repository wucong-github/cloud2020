package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult creart(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);
        System.out.println("12334");
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(404, "插入数据库失败", null);
        }
    }


    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        System.out.println(id);

        log.info("****查询结果：" + paymentById);

        if (null != paymentById) {
            return new CommonResult(200, "查询数据库成功", paymentById);
        } else {
            return new CommonResult(404, "查询数据库失败", null);
        }
    }

}
