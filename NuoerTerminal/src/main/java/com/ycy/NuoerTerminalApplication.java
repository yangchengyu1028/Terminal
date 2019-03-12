package com.ycy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "com")
@EnableEurekaClient
@MapperScan("com.ycy.mapper")
@Component("com.ycy.entity")
public class NuoerTerminalApplication {

    public static void main(String[] args) {
        SpringApplication.run(NuoerTerminalApplication.class, args);
    }

}
