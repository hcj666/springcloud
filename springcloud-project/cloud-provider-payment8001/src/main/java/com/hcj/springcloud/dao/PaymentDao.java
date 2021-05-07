package com.hcj.springcloud.dao;

import com.hcj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentDao {

    @Insert("insert into payment values(null,#{serial})")
    public int create(Payment payment);//新增payment

    @Select("select * from payment where id=#{id}")
    public Payment getPaymentById(@Param("id") Long id);
}
