package com.online.edu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //发现注册中心里面其他的服务
public class EduServiceApplication {
    public static void main(String[] args) {
            SpringApplication.run(EduServiceApplication.class, args);
        }
}
