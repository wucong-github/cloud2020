package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴聪
 * @Date 2020/4/8 0008
 * @since 7.1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level getFeign() {

        return Logger.Level.FULL;
    }
}
