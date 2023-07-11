package com.kishko.photoservice;

import com.kishko.photoservice.entities.Attachment;
import com.kishko.photoservice.repositories.AttachmentRepository;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = {"com.kishko.userservice.entities", "com.kishko.photoservice.entities"})
//@EnableJpaRepositories(basePackages = {"com.kishko.userservice.repositories", "com.kishko.photoservice.repositories"})
//@EntityScan(basePackageClasses = {Attachment.class, User.class})
//@EnableJpaRepositories(basePackageClasses = {AttachmentRepository.class, UserRepository.class})
public class PhotoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoServiceApplication.class, args);
	}

}
