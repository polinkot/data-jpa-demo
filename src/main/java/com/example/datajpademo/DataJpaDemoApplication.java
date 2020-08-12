package com.example.datajpademo;

import com.example.datajpademo.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataJpaDemoApplication implements ApplicationRunner {

    @Autowired
    private InitService initService;

    public static void main(String[] args) {
        SpringApplication.run(DataJpaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        initService.init();
    }
}
