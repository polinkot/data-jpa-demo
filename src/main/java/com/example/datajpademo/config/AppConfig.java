package com.example.datajpademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.datajpademo.repository")
@Configuration
public class AppConfig {
}
