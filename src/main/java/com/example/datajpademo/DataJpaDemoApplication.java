package com.example.datajpademo;

import com.example.datajpademo.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(InitService initService) {
        return (args) -> initService.init();
    }
}
