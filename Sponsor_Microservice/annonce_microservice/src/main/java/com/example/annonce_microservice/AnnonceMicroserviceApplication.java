package com.example.annonce_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnnonceMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnonceMicroserviceApplication.class, args);
    }

}
