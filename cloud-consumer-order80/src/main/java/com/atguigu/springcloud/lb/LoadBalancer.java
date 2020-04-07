package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author 吴聪
 * @Date 2020/4/7 0007
 * @since 7.1.0
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
