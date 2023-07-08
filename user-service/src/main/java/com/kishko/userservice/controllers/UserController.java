package com.kishko.userservice.controllers;

import com.kishko.userservice.dtos.AdvancedStockDTO;
import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserById(id, userDTO), HttpStatus.OK);
    }

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

    @PutMapping("/{userId}/stock/{stockId}/{count}")
    public ResponseEntity<UserDTO> updateUserStocks(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId, @PathVariable("count") Integer count) throws Exception {
        return new ResponseEntity<>(userService.updateUserStocks(userId, stockId, count), HttpStatus.OK);
    }

    @PutMapping("/{userId}/incBalance/{amount}")
    public ResponseEntity<UserDTO> increaseUserBalance(@PathVariable("userId") Long userId, @PathVariable("amount") Double amount) throws Exception {
        return new ResponseEntity<>(userService.increaseUserBalance(userId, amount), HttpStatus.OK);
    }

    @PutMapping("/{userId}/decBalance/{amount}")
    public ResponseEntity<UserDTO> decreaseUserBalance(@PathVariable("userId") Long userId, @PathVariable("amount") Double amount) throws Exception {
        return new ResponseEntity<>(userService.decreaseUserBalance(userId, amount), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

    @DeleteMapping("/{userId}/stock/{stockId}/{count}")
    public ResponseEntity<UserDTO> deleteUserStocks(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId, @PathVariable("count") Integer count) throws Exception {
        return new ResponseEntity<>(userService.deleteUserStocks(userId, stockId, count), HttpStatus.OK);
    }

}
