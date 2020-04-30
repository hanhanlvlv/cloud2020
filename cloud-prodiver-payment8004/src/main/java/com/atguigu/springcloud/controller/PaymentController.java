package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;


    @PostMapping("/payment/insertPayment")
    public CommonResult insertPayment(@RequestBody Payment payment){
        int count = paymentService.insertPayment(payment);
        if (count > 0){
            return new CommonResult(200,"插入" + count + "条记录成功,servicePort:" + servicePort,count);
        }
        return new CommonResult(444,"插入" + count + "条记录失败",null);
    }


    @GetMapping("/payment/findOne/{id}")
    public CommonResult<Payment> findOne(@PathVariable("id")Long id){
        Payment payment = paymentService.findOne(id);
        if (payment != null){
            return new CommonResult(200,"查询成功,servicePort:" + servicePort,payment);
        }
        return new CommonResult(444,"查询失败",null);
    }


    @GetMapping("/payment/lb")
    public String lb(){

        return servicePort;

    }


}
