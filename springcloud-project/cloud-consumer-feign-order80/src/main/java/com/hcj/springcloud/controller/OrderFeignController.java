package com.hcj.springcloud.controller;

import com.hcj.springcloud.entities.CommonResult;
import com.hcj.springcloud.entities.Payment;
import com.hcj.springcloud.service.PaymentFeignService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;

@Controller
@RequestMapping("/order/consumer/")
public class OrderFeignController {

    @Autowired(required = false)
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "getPaymentById/{id}")
    @ResponseBody
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "payment/feign/timeout")
    @ResponseBody
    public String paymentFeignTimeOut(){
        return paymentFeignService.paymentFeignTimeOut();
    }

}
