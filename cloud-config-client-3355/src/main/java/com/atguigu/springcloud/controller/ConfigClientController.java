package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.context.annotation.ScopedProxyMode.DEFAULT;

@RestController
@RefreshScope(proxyMode = DEFAULT)  //加了这个注解@Value获取的值为null,将proxyMode属性的值换成DEFAULT又可以获取到值
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    private String getConfigInfo(){
        return "直接获取:" + configInfo +"        访问到了";
    }


}
