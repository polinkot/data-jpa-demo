package com.example.datajpademo.config;

import com.example.datajpademo.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@EnableJpaRepositories(basePackages = "com.example.datajpademo.repository")
@EntityScan("com.example.datajpademo.model")
@Configuration
public class AppConfig {

    //populate DB from code
    @Bean
    public CommandLineRunner demo(InitService initService) {
        return (args) -> initService.init();
    }

    //populate DB from json file
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("data.json")});
        return factory;
    }
}
