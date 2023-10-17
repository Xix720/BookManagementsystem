package com.example.dmsserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dmsserver.mapper")
public class DmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmsServerApplication.class, args);
    }

}
