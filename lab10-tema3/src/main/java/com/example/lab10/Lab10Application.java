package com.example.lab10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.lab10.service"})
public class Lab10Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab10Application.class, args);
    }

}
