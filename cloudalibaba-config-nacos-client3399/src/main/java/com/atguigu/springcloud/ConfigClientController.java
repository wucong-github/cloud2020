package com.atguigu.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 3377和3399 端口是配置中心的消费方，nacos是配置中心
 * 支持Nacos的动态刷新功能,
 * @RefreshScope 配置动态刷新标签，
 * nacos 相当于eureka + config + Bus
 * Bus 将各个微服务分在了同一个主题下，对nacos服务端的一次刷新请求，处处生效
 *
 */
@RestController
@RequestMapping
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {

        return configInfo;
    }

    @GetMapping("/config/info2")
    public String getConfigInfo2() {

        return "读取到的相关远程配置："+configInfo;
    }

}




