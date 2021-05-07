package com.hcj.springcloud.service.impl;

import com.hcj.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentHystrixService {


    @Override
    public String paymentInfo_OK(Integer id) {//paymentInfo_OK的兜底方法
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
        //可以封装一个兜底方法，返回的是 return "-----PaymentFallbackService fall" + 传入的方法名字+ ,o(╥﹏╥)o";
        //这样每个兜底方法就都可以调用这个方法了
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {//paymentInfo_TimeOut的兜底方法
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }


}
