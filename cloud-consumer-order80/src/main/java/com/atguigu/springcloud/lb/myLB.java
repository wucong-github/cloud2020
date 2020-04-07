package com.atguigu.springcloud.lb;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吴聪
 * @Date 2020/4/7 0007
 * @since 7.1.0
 */
@Component
public class myLB implements LoadBalancer {
    /**
     *  高并发的情况下，i++无法保证原子性，往往会出现问题，所以引入AtomicInteger类。
     *  current 是原子整数 ，首先设置为0
     *  然后index 的值是 current+1 所以是1 代表第一次访问
     *  compareAndSet（）方法是比较 current 和 next 是否相同
     *  如果不同，则更新atomicInteger的值 ，并且返回true
     *  这里我们使用 ！ 非，终止循环
     *
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

     public final int getAndIncrement(){
         int current;
         int next;

         do{
             current = this.atomicInteger.get();
             System.out.println(current);
             next = current >=  2147483647 ? 0 : current + 1;
             System.out.println(next);

             // System.out.println(!this.atomicInteger.compareAndSet(current,next));
         }while(!this.atomicInteger.compareAndSet(current,next));
         System.out.println("*****第"+next+"次访问*****");
         return next;
     }


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        /*
         *  访问次数 对 服务器数量 取余
         */
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
