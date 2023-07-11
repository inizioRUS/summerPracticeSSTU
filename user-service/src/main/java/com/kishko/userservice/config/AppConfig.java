package com.kishko.userservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(value = "myConfig")
@EnableJpaRepositories(basePackages = {"com.kishko.userservice.repositories", "com.kishko.photoservice.repositories"})
@EntityScan(basePackages = {"com.kishko.userservice.entities", "com.kishko.photoservice.entities"})
@ComponentScan(basePackages = {"com.kishko.photoservice", "com.kishko.userservice"})
public class AppConfig {
}
