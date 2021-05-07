package com.hcj.springcloud.service;

import com.hcj.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping(value = "/payment/hystrix/paymentInfo_OK/{id}")
    @ResponseBody
    public String paymentInfo_OK(@PathVariable("id") Integer id) ;

    @GetMapping(value = "/payment/hystrix/paymentInfo_TimeOut/{id}")
    @ResponseBody
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) ;


}
