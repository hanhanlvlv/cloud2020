package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/findOne/{id}")
    public CommonResult<Payment> findOne(@PathVariable("id")Long id){
        return paymentService.findOne(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String timeout(){
        return paymentService.timeout();
    }

}
