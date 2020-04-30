package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {

    int insertPayment(Payment payment);

    Payment findOne(Long id);

}
