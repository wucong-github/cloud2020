package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴聪
 * @Date 2020/5/11 0011
 * @since 1.0.0
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private  String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String getConfigInfo(){
        return  "端口号："+serverPort+"远程配置"+configInfo;
    }

}
