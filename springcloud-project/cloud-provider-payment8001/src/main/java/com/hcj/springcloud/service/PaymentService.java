package com.hcj.springcloud.service;

import com.hcj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PaymentService {

    public int create(Payment payment);


    public Payment getPaymentById(Long id);
}
