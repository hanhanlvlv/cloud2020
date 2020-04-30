package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id")Integer id){

        String reslut = paymentHystrixService.payment_ok(id);

        return reslut;

    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id")Integer id){

        String reslut = paymentHystrixService.payment_timeout(id);

        return reslut;

    }


    @GetMapping("/payment/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id){

        String reslut = paymentHystrixService.payment_hystrix(id);
        return reslut;

    }

}
