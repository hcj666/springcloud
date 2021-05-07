package com.hcj.springcloud.controller;


import com.hcj.springcloud.entities.CommonResult;
import com.hcj.springcloud.entities.Payment;
import com.hcj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j //日志
@RestController //相当于@responseBody+@Controller,和之前做的毕设项目不同的是，之后的方法上都不用写@responseBody了
@RequestMapping("/payment/")
public class PaymentController {
    @Autowired(required = false)
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    //以下用RestFul风格，不再用如RequestMapping("create")了
    @PostMapping(value = "create")
    public CommonResult create(@RequestBody Payment payment){//写操作，用POST
        int i = paymentService.create(payment);
        if(i>0){
            log.info("********插入结果"+i);
            return new CommonResult(200,"插入数据库成功,port:"+serverPort,i);
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

}
