package com.hcj.springcloud.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/payment/")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    String serverPort; //spring自动注入yml里server.port的值


    @RequestMapping("paymentZk")
    @ResponseBody
    public String paymentZk(){
        return "springcloud with zookeeper:" + serverPort + UUID.randomUUID().toString();
    }


}
