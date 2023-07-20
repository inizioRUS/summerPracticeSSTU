package com.kishko.userservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(value = "myConfig")
@EnableJpaRepositories(basePackages = {"com.kishko.userservice.repositories", "com.kishko.photoservice.repositories"})
@EntityScan(basePackages = {"com.kishko.userservice.entities", "com.kishko.photoservice.entities", "com.ryasnov.transactionservice.entities"})
@ComponentScan(basePackages = {"com.kishko.photoservice", "com.kishko.userservice"})
public class AppConfig {

    @Bean
    public WebClient localApiClient() {
        return WebClient.create("http://80.249.145.114:8000/today/");
    }

}
