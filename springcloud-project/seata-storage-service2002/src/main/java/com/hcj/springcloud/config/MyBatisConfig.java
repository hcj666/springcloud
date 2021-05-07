package com.hcj.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.hcj.springcloud.dao"})
public class MyBatisConfig {

}
