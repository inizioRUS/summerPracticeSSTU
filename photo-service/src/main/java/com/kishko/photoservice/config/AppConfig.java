package com.kishko.photoservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration(value = "config")
@EntityScan(basePackages = {"com.kishko.userservice.entities", "com.kishko.photoservice.entities"})
@EnableJpaRepositories(basePackages = {"com.kishko.userservice.repositories", "com.kishko.photoservice.repositories"})
public class AppConfig {

}
