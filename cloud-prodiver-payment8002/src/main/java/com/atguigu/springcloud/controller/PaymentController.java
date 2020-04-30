package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @Resource
    private DiscoveryClient discoveryClient;

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


    @GetMapping("/payment/getDiscovery")
    public Object getDiscovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******service：" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String lb(){

        return servicePort;

    }

    @GetMapping("/payment/feign/timeout")
    public String timeout(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return servicePort;
    }

}
