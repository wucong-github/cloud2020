package com.atguigu.springcloud.server;

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
    public String paymentInfo_OK(Integer id) {

        return "线程池：" + Thread.currentThread().getName() + " paymentInfo,id:" + id + "\t" + "返回成功";
    }

    // 延迟3秒 返回id
    public String paymenInfo_TimeOut(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池：" + Thread.currentThread().getName()
                + " paymentInfo_TimeOut,id:" + id + "\t"
                + "返回成功,耗时" + timeNumber + "秒";

    }


}
