package com.stxy.smsadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.stxy.smsadmin.dataobject.mapper")
@EnableAspectJAutoProxy
public class SmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsAdminApplication.class, args);
    }
}
