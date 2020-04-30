package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalanced;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    //单机版配置
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //集群版配置
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalanced loadBalanced;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/insertPayment")
    public CommonResult insertPayment(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/insertPayment",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/findOne/{id}")
    public CommonResult findOne(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/findOne/" + id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String lb(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        ServiceInstance instance = loadBalanced.instances(instances);

        URI uri = instance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb",String.class);
    }

}
