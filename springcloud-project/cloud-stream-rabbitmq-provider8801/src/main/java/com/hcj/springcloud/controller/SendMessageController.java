package com.hcj.springcloud.controller;

import com.hcj.springcloud.service.IMessageProvider;
import com.netflix.discovery.converters.Auto;
import javafx.scene.shape.VLineTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;

@Controller
@RequestMapping("/send/")
public class SendMessageController {


    @Autowired(required = false)
    IMessageProvider iMessageProvider;

    @GetMapping(value = "message")
    @ResponseBody
    public String send(){
        return iMessageProvider.send();
    }

}
