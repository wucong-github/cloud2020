package com.atguigu.springcloud.server;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 吴聪
 * @Date 2020/4/9 0009
 * @since 7.1.0
 */

@Service
public class PaymentService {

    // 正常的方法，直接返回 id
    /**
     *  使用@HystrixCommand
     *  不指定备用方法的话就会使用默认备用方法
     *
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

}
