package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {

    public String payment_ok(Integer id){

        return "线程池：" + Thread.currentThread().toString() + "   id：" + id + "O(∩_∩)O哈哈~";

    }


    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })
    public String payment_timeout(Integer id){

//        int timeout = 1;
//
//        try {
//            TimeUnit.SECONDS.sleep(timeout);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return "线程池：" + Thread.currentThread().toString() + "   id：" + id + "O(∩_∩)O哈哈~" + "访问时间：";

    }

    public String payment_timeoutHandler(Integer id){

        return "线程池：" + Thread.currentThread().toString() + "   id：" + id + "o(╥﹏╥)o";

    }

    @HystrixCommand(fallbackMethod = "payment_hystrixEorr",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
    })
    public String payment_hystrix(Integer id){

        String simpleUUID = IdUtil.simpleUUID();

        if (id < 0){

            throw  new RuntimeException("****id 不能为负数");

        }

        return "传入的ID是：" + id + "    订单的流水号是" + simpleUUID;

    }


    public String payment_hystrixEorr(Integer id){
        return "id不能为负数" + id;
    }
}
