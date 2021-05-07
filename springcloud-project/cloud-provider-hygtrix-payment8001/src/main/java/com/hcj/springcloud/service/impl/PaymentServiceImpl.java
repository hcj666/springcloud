package com.hcj.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hcj.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    //模拟一个线程请求成功
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_ok id:"+id;
    }

    //模拟一个线程请求等待超时
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler"/*指定善后方法名*/,commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
//        int r = 10/0;
        int sleep=5;
        try { TimeUnit.SECONDS.sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_ok id:"+id+"耗时"+sleep;
    }

    //用来善后兜底的方法
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_ok id:"+id+
                " 8001系统繁忙或者运行报错，请稍后再试";

    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(Integer id) {
        if(id<0){
            throw new RuntimeException("id不能为负数！");
        }else{
            String serialNumber = IdUtil.simpleUUID();
            return Thread.currentThread().getName()+"流水号"+serialNumber;
        }
    }

    //paymentCircuitBreaker的兜底方法
    public String paymentCircuitBreaker_fallback( Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
