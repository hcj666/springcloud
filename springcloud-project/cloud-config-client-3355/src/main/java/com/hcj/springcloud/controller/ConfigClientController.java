package com.hcj.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    String configInfo;

    @GetMapping(value = "configInfo")
    @ResponseBody
    public String getConfigInfo()
    {
        return configInfo;
    }

}
