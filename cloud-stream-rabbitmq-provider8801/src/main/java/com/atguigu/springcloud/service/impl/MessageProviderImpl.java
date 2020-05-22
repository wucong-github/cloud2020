package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @EnableBinding 表明这是一个绑定器
 * Source.class   消息的生产者
 * MessageChannel  消息通道 output
 *
 * send 消息发送方法
 *
 */
@Component
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {
    @Resource
    private MessageChannel output;
    static  int i =0;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();

         i++;
        // 使用消息通道的send()方法发送，   build()构建消息
        output.send(MessageBuilder.withPayload(serial+"，数字："+i).build());
        System.out.println("@EnableBinding .... MessageProviderImpl");
        return serial;
    }





}
