package com.hcj.springcloud.controller;


import com.hcj.springcloud.entities.CommonResult;
import com.hcj.springcloud.entities.Payment;
import com.hcj.springcloud.service.PaymentService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j //日志
@RestController //相当于@responseBody+@Controller,和之前做的毕设项目不同的是，之后的方法上都不用写@responseBody了
@RequestMapping("/payment/")
public class PaymentController {
    @Autowired(required = false)
    PaymentService paymentService;

    @Value("${server.port}")//value注解可以拿到application.yml里面的指，这里拿的是端口号，再通过spring自动注入进对象中
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    //以下用RestFul风格，不再用如RequestMapping("create")了
    @PostMapping(value = "create")
    public CommonResult create(@RequestBody Payment payment){//写操作，用POST
        System.out.println(payment);
        int i = paymentService.create(payment);
        if(i>0){
            log.info("********插入结果"+i);
            return new CommonResult(200,"插入数据库成功，port:"+serverPort,i);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }
    @GetMapping(value = "getPaymentById/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){//这里是读操作，用Get ，如果是修改，用Put，
        // 删除，用Delete
        Payment payment = paymentService.getPaymentById(id);
        if(payment!=null){
            log.info("********查询结果"+payment);
            return new CommonResult(200,"查询成功了！,port:"+serverPort,payment);
        }else{
            return new CommonResult(444,"查询失败,id:"+id,null);
        }
    }

    @GetMapping("zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to here, O(∩_∩)O哈哈~";
    }


    @GetMapping(value = "discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices(); //获得eureka服务器上的所有服务应用（application）
        for (String service : services) {
            log.info("element:"+service);//打印一下每个服务应用
        }

        //获得名为CLOUD-PROVIDER-SERVICE 的服务应用下的所有实例【微服务端口（主机）】
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");


        for (ServiceInstance instance : instances) {
            log.info("ServiceId:"+instance.getServiceId()+"host:"+instance.getHost()+"port:"
                    +instance.getPort()+"uri:"+instance.getUri());//输出一下该服务应用的所有信息
        }
        return this.discoveryClient; //如果返回的是discoveryClient，那么就是返回所有的服务应用的所有信息
        //如果返回的是this.discoveryClient，那么就是返回请求该微服务远程调用接口的时候该微服务应用名下的所有信息
    }


    @GetMapping(value = "feign/timeout")
    public String paymentFeignTimeOut(){
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;

    }






}
