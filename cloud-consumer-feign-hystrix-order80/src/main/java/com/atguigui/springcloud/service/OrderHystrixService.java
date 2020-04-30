package com.atguigui.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderHystrixService implements OrderFeignHystrixService {

    @Override
    public String payment_ok(Integer id) {
        return "----------Payemnt_OK " + id;
    }

    @Override
    public String payment_timeout(Integer id) {
        return "-----------Payment_TimeOut " + id;
    }


}
