package com.kishko.userservice.controllers;

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

    @PutMapping("/{userId}/stock/{stockId}")
    public ResponseEntity<UserDTO> updateUserStocks(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserStocks(userId, stockId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/stock/{stockId}")
    public ResponseEntity<UserDTO> deleteUserStocks(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUserStocks(userId, stockId), HttpStatus.OK);
    }

}
