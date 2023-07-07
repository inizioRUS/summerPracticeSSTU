package com.kishko.userservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.StockRepository;
import com.kishko.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO userDTO;
    private UserDTO userDTO2;

    @BeforeEach
    void setUp() {

        userService = new UserServiceImpl(userRepository, stockRepository);

        userDTO = UserDTO.builder()
                .id(1L)
                .name("Dima")
                .surname("Kishko")
                .email("kishko.2003@list.ru")
                .password("amirochka05")
                .role(Role.USER)
                .balance(15000.0)
                .stocks(null)
                .build();

        userDTO2 = UserDTO.builder()
                .id(2L)
                .name("Dima")
                .surname("Kishko")
                .email("kishko.2004@list.ru")
                .password("amirochka05")
                .role(Role.USER)
                .balance(15000.0)
                .stocks(null)
                .build();

    }

    @Test
    void getAllUsers() {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.findAll()).thenReturn(
                List.of(toUser(userDTO), toUser(userDTO2)));

        assertEquals(userService.getAllUsers().get(0).getEmail(), userDTO.getEmail());
        assertEquals(userService.getAllUsers().get(1).getEmail(), userDTO2.getEmail());

    }

    @Test
    void getUserById() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.of(toUser(userDTO)));

        UserDTO savedUser = userService.getUserById(1L);

        assertThat(savedUser).isNotNull();
        assertEquals(savedUser.getEmail(), userDTO.getEmail());

    }

    @Test
    void getUserByEmail() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.getUserByEmail(userDTO.getEmail())).thenReturn(Optional.of(toUser(userDTO)));

        UserDTO savedUser = userService.getUserByEmail(userDTO.getEmail());

        assertThat(savedUser).isNotNull();

    }

    @Test
    void updateUserById() throws UserNotFoundException {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.save(any(User.class))).thenReturn(toUser(userDTO));

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(toUser(userDTO)));

        UserDTO savedUser = userService.updateUserById(userDTO.getId(), userDTO);

        assertThat(savedUser).isNotNull();

    }

    @Test
    void deleteUserById() {

        mock(UserDTO.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.of(toUser(userDTO)));

        assertAll(() -> userService.deleteUserById(1L));

    }

    @Test
    @Disabled
    void updateUserStocks() {
    }

    @Test
    @Disabled
    void deleteUserStocks() {
    }

    public UserDTO toDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .balance(user.getBalance())
                .name(user.getName())
                .surname(user.getSurname())
                .stocks(user.getStocks())
                .build();
    }

    public User toUser(UserDTO userDTO) {

        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .balance(userDTO.getBalance())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .stocks(userDTO.getStocks())
                .build();
    }
}