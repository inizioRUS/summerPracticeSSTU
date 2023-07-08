package com.kishko.userservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.services.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {

        userDTO = UserDTO.builder()
                .id(1L)
                .name("Dima")
                .surname("Kishko")
                .email("kishko.2003@list.ru")
                .password("amirochka05")
                .role(Role.USER)
                .balance(15000.0)
                .advancedStocks(null)
                .build();

    }

    @Test
    void getAllUsers() throws Exception {

        when(userService.getAllUsers()).thenReturn(List.of(userDTO));

        ResultActions response = mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getUserById() throws Exception {

        Long id = 1L;

        when(userService.getUserById(id)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getUserByEmail() throws Exception {

        String email = "kishko.2003@list.ru";

        when(userService.getUserByEmail(email)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(get("/users/email?email=" + email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void updateUserById() throws Exception {

        Long id = 1L;

        when(userService.updateUserById(id, userDTO)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void deleteUserById() throws Exception {

        Long id = 1L;

        when(userService.deleteUserById(id)).thenReturn("successful deleted");

        ResultActions response = mockMvc.perform(delete("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void increaseUserBalance() throws Exception {

        Long id = userDTO.getId();
        Double amount = 1000.0;

        when(userService.increaseUserBalance(id, amount)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(put("/users/" + id + "/incBalance/" + amount)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance", CoreMatchers.is(userDTO.getBalance())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void decreaseUserBalance() throws Exception {

        Long id = userDTO.getId();
        Double amount = 1000.0;

        when(userService.decreaseUserBalance(id, amount)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(put("/users/" + id + "/decBalance/" + amount)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance", CoreMatchers.is(userDTO.getBalance())))
                .andDo(MockMvcResultHandlers.print());

    }

}