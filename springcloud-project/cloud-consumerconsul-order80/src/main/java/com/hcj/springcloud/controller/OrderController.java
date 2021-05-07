package com.hcj.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/order/")
public class OrderController {

    public static final String INVOKE_URL = "http://consul-provider-payment";


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/consul")
    @ResponseBody
    public String consul(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/paymentConsul",String.class);
        return result;
    }






}
