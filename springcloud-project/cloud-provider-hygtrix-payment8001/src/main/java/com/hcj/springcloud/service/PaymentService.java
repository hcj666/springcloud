package com.hcj.springcloud.service;

public interface PaymentService {
    String paymentInfo_OK(Integer id);

    String paymentInfo_TimeOut(Integer id);

    public String paymentCircuitBreaker(Integer id);

}
