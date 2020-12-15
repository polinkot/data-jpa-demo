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

    //POSTGRES
    public static final String UUID_PK_DEFAULT = "uuid_generate_v4()";

    //H2
//    public static final String UUID_PK_DEFAULT = "random_uuid()";

    @Bean
    public CommandLineRunner demo(InitService initService) {
        return (args) -> initService.init();
    }

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("data.json")});
        return factory;
    }
}
