package com.kishko.authservice.services;
import com.kishko.authservice.config.AppConfig;
import com.kishko.authservice.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.kishko.authservice.repositories.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    private User user;

    @BeforeEach
    void setUp() {

        authenticationService = new AuthenticationServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager);

        user = User.builder()
                .id(1L)
                .email("kishko.2003@list.ru")
                .password("katushka03")
                .role(Role.USER)
                .build();

    }

    @Test
    void register() {

        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.save(any(User.class))).thenReturn(user);

        AuthenticationResponse savedUser = authenticationService.register(RegisterRequest.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build());

        System.out.println(savedUser);

        assertEquals(savedUser.getEmail(), user.getEmail());
        assertThat(savedUser).isNotNull();

    }

    @Test
    void authenticate() {

        mock(User.class);
        mock(RegisterRequest.class);
        mock(UserRepository.class);

        when(userRepository.getUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        AuthenticationResponse targetUser = authenticationService.authenticate(AuthenticationRequest.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build());

        System.out.println(targetUser);

        assertEquals(targetUser.getEmail(), user.getEmail());
        assertThat(targetUser).isNotNull();

    }
}