package com.kishko.userservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO increaseUserBalance(Long userId, Double amount) throws UserNotFoundException;

    UserDTO decreaseUserBalance(Long userId, Double amount) throws Exception;

    UserDTO toDTO(User user);

    User toUser(UserDTO userDTO);

    UserDTO getUserById(Long id) throws UserNotFoundException;

    UserDTO getUserByEmail(String email) throws UserNotFoundException;

    UserDTO updateUserById(Long id, UserDTO userDTO) throws UserNotFoundException;

    String deleteUserById(Long id) throws UserNotFoundException;

    UserDTO updateUserStocks(Long userId, Long stockId, Integer count) throws Exception;

    UserDTO deleteUserStocks(Long userId, Long stockId, Integer count) throws Exception;

    UserDTO addUserWishlistStock(Long userId, Long stockId) throws UserNotFoundException;

    UserDTO deleteUserWishlistStock(Long userId, Long stockId) throws Exception;
}
