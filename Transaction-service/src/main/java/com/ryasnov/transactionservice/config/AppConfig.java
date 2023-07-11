package com.ryasnov.transactionservice.config;

import com.kishko.userservice.repositories.UserRepository;

import com.ryasnov.transactionservice.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = {"com.kishko.userservice.repositories","com.ryasnov.transactionservice.repositories"})
@EntityScan(basePackages = {"com.kishko.userservice.entities", "com.ryasnov.transactionservice.entities"})
@ComponentScan({"com.kishko.userservice.services"})
public class AppConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;


}
