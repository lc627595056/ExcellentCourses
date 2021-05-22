package com.online.edu.ek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EkServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EkServerApplication.class);
    }
}
