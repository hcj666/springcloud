package com.hcj.springcloud.controller;

import com.hcj.springcloud.service.PaymentService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Controller
@RequestMapping("/payment/")
@Slf4j
public class PaymentController {


    @Autowired(required = false)
    PaymentService paymentService;

    @Value("${server.port}")
    String serverPort;
    @GetMapping(value = "hystrix/paymentInfo_OK/{id}")
    @ResponseBody
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String s = paymentService.paymentInfo_OK(id);
        log.info("*******************result:"+s);
        return s;
    }
    @GetMapping(value = "hystrix/paymentInfo_TimeOut/{id}")
    @ResponseBody
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String ss = paymentService.paymentInfo_TimeOut(id);
        log.info("*******************result:"+ss);
        return ss;
    }

    //====服务熔断
    @GetMapping(value = "hystrix/paymentCircuitBreaker/{id}")
    @ResponseBody
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("********************result:"+result);
        return result;
    }

}
