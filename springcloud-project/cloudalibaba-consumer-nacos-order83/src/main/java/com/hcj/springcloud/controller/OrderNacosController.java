package com.hcj.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/order/")
public class OrderNacosController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    String serverURL;


    @GetMapping(value = "/consumer/payment/nacos/{id}")
    @ResponseBody
    public String getPaymentInfo(@PathVariable("id") Integer id){
       return  restTemplate.getForObject(serverURL + "/payment/getPayment/" + id,String.class);
    }




}
