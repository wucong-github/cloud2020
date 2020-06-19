package com.atguigu.springcloud.server;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author 吴聪
 * @Date 2020/4/9 0009
 * @since 7.1.0
 */

@Service
public class PaymentService {
    private final String string = "circuitBreaker.enabled";

    // 正常的方法，直接返回 id

    /**
     * 使用@HystrixCommand
     * 不指定备用方法的话就会使用默认备用方法
     */
    @HystrixCommand
    public String paymentInfo_OK(Integer id) {

        return "线程池：" + Thread.currentThread().getName() + " paymentInfo,id:" + id + "\t" + "返回成功";
    }

    // 延迟3秒 返回id
    @HystrixCommand(fallbackMethod = "fallbackOut", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymenInfo_TimeOut(Integer id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池：" + Thread.currentThread().getName()
                + " paymentInfo_TimeOut,id:" + id + "\t"
                + "返回成功,耗时" + timeNumber + "秒";

    }

    public String fallbackOut(Integer id) {

        return "线程池：" + Thread.currentThread().getName()
                + " fallbackOut,id:" + id + "\t"
                + "8001系统繁忙，请稍后再试 ";

    }


    // ==== 如果服务熔断 ====
    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name =  "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }


    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        // 这是一个方法
        return "id 不能为负数,请稍后再试， id: " + id;
    }


}
