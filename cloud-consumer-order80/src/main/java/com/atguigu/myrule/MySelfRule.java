package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

  /*  @Bean
    public IRule getIRule(){
        return new RoundRobinRule_ZHF();//自定义负载均衡方式
    }*/
}
