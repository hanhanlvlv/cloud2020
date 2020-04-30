package com.atguigui.springcloud.controller;

import com.atguigui.springcloud.service.OrderFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "payment_timeoutHandler")
public class OrderFeignHystrixController {

    @Resource
    private OrderFeignHystrixService orderFeignHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id")Integer id){
        String reslut = orderFeignHystrixService.payment_ok(id);
        return reslut;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    @HystrixCommand
    public String payment_timeout(@PathVariable("id")Integer id){
        //int i = 10 / 0;
        String reslut = orderFeignHystrixService.payment_timeout(id);
        return reslut;
    }

    public String payment_timeoutHandler(){
        return "8080服务器出错，请求超时或者自身问题，请重试";
    }
}
