package com.kishko.authservice.repositories;

import com.kishko.authservice.entities.Role;
import com.kishko.authservice.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
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