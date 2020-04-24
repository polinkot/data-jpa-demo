package com.example.datajpademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.datajpademo.repository")
@Configuration
public class AppConfig {

    //POSTGRES
    public static final String UUID_PK_DEFAULT = "uuid_generate_v4()";

    //H2
//    public static final String UUID_PK_DEFAULT = "random_uuid()";
}
