package com.hcj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hcj.springcloud.entities.CommonResult;
import com.hcj.springcloud.entities.Payment;
import com.hcj.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/rateLimit/")
@Controller
public class RateLimitController {

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,//<-------- 自定义限流处理类
            blockHandler = "handlerException2")//<-----------
    @ResponseBody
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
    }


    @GetMapping("byUrl")
    @SentinelResource(value = "byUrl")
    @ResponseBody
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }






}
