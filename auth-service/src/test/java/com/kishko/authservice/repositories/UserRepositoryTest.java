package com.kishko.authservice.repositories;

import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@EnableJpaRepositories(basePackages = "com.kishko.userservice.repositories")
@EntityScan("com.kishko.userservice.entities")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1L)
                .email("kishko.2003@list.ru")
                .password("katushka03")
                .role(Role.USER)
                .build();

    }

    @AfterEach
    void tearDown() {

        userRepository.deleteAll();

    }

    @Test
    void getUserByEmail() {

        userRepository.save(user);

        Optional<User> savedUser = userRepository.getUserByEmail(user.getEmail());

        System.out.println(user);

        assertThat(savedUser).isNotNull();

    }
}