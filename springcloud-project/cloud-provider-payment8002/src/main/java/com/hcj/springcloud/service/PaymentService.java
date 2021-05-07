package com.hcj.springcloud.service;

import com.hcj.springcloud.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);


    public Payment getPaymentById(Long id);
}
