package com.kishko.userservice.repositories;

import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.User;
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
                .name("Dima")
                .surname("Kishko")
                .email("kishko.2003@list.ru")
                .password("amirochka05")
                .role(Role.USER)
                .balance(15000.0)
                .advancedStocks(null)
                .build();

        userRepository.save(user);

    }

    @AfterEach
    void tearDown() {

        user = null;
        userRepository.deleteAll();

    }

    @Test
    void getUserByEmail() {

        String email = "kishko.2003@list.ru";

        User savedUser = userRepository.getUserByEmail(email).get();

        System.out.println(savedUser);

        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getName(), user.getName());

    }
}