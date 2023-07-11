package com.ryasnov.transactionservice;

import com.kishko.userservice.entities.AdvancedStock;
import com.ryasnov.transactionservice.entities.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackageClasses = {AdvancedStock.class, Transaction.class })
//@EntityScan(basePackages = {"com.kishko.userservice.entities.AdvancedStock", "com.ryasnov.transactionservice.entities.Transaction"})
//@EnableJpaRepositories(basePackages = {"com.kishko.userservice.repositories.UserRepository","com.ryasnov.transactionservice.repositories.TransactionRepository"})
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

}
