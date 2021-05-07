package com.hcj.springcloud.controller;

import com.hcj.springcloud.domain.CommonResult;
import com.hcj.springcloud.domain.Order;
import com.hcj.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order/")
public class OrderController {

    @Autowired(required = false)
    OrderService orderService;

    @GetMapping(value = "create")
    @ResponseBody
    public CommonResult create(Order order)
    {

        System.out.println(orderService);

        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }



}
