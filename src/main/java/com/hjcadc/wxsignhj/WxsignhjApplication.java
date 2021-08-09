package com.hjcadc.wxsignhj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.hjcadc.wxsignhj.mapper")
@SpringBootApplication
public class WxsignhjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxsignhjApplication.class, args);
    }

}
