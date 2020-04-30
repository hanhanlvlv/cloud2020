package com.atguigui.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = OrderHystrixService.class)
public interface OrderFeignHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String payment_ok(@PathVariable("id")Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String payment_timeout(@PathVariable("id")Integer id);

}
