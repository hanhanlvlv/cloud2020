package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LB implements LoadBalanced {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getInstances(){
        int currut;
        int next;
        do {
            currut = this.atomicInteger.get();
            next = currut >= 2147483647 ? 0 : currut + 1;
        }while (!this.atomicInteger.compareAndSet(currut,next));
        System.out.println("*****第" + next + "次调用接口");
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstance) {

        int index = getInstances() % serviceInstance.size();
        ServiceInstance instance = serviceInstance.get(index);
        return instance;
    }
}
