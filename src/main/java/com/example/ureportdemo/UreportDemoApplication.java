package com.example.ureportdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.ureportdemo.mapper")
public class UreportDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UreportDemoApplication.class, args);
    }

}
