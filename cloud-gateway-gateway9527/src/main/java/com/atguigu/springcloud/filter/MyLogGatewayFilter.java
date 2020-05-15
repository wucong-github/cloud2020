package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 自定义全局过滤器 GlobalFilter
 *
 * @author 吴聪
 * @Date 2020/5/8 0008
 * @since 1.0.0
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("**********come in MyLogGatewayFilter :" + new Date());
        // 要求前端发送的请求，要有"uname" 参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if (null == uname) {

            log.info("*******用户名为null 非法用户");
            // 如果没有这个参数，则返回前端的响应 NOT_ACCEPTABLE 不能接收
            // 表示过滤器不允许请求通过
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            // 让这个不通过的请求返回到前端
            return exchange.getResponse().setComplete();
        }
        // 如果通过了过滤器，就进入过滤链的下一个过滤器

        return chain.filter(exchange);
    }

    @Override public int getOrder() {

        // 数字越小，优先级越高
        return 0;
    }
}
