package com.hcj.springcloud.service.impl;


import com.hcj.springcloud.dao.PaymentDao;
import com.hcj.springcloud.entities.Payment;
import com.hcj.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired(required = false)
    PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        System.out.println(payment);
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
