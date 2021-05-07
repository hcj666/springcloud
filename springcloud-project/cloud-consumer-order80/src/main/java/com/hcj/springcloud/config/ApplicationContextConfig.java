package com.hcj.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //相当于配置类，即Spring里的applicationContext.xml
public class ApplicationContextConfig {

    //相当于Spring里的 <bean id="restTemplate" class="org.springframework.web.client.RestTemplate">
    @Bean //写了bean相当于将这个对象注入到了spring容器中
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


}
