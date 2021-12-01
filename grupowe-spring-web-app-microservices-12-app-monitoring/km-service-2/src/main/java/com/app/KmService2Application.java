package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KmService2Application {

    public static void main(String[] args) {
        SpringApplication.run(KmService2Application.class, args);
    }

}
