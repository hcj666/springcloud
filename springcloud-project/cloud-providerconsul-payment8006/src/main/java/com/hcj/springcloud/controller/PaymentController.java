package com.hcj.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/payment/")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("paymentConsul") //GetMapping(value="paymentConsul")应该也可以，
    // 因为虽然没数据库，但是也是从配置文件中读数据（serverPort）
    @ResponseBody
    public String paymentConsul(){
        return "springcloud with consul:"+serverPort+ UUID.randomUUID().toString();
    }

}
