package com.kishko.userservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.Role;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.StockRepository;
import com.kishko.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO).toList();
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("There is no user with id: " + id)
        );

        return toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException {

        User user = userRepository.getUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException("There is no user with email: " + email)
        );

        return toDTO(user);
    }

    @Override
    public UserDTO updateUserById(Long id, UserDTO userDTO) throws UserNotFoundException {

        UserDTO userDB = getUserById(id);

        String email = userDTO.getEmail();

//        String password = userDTO.getPassword();

        String name = userDTO.getName();

        String surname = userDTO.getSurname();

        if (Objects.nonNull(email) && !"".equalsIgnoreCase(email)) {
            userDB.setEmail(email);
        }

//        TODO ДОДЕЛАТЬ ИЗМЕНЕНИЕ ПАРОЛЯ С ПРЕОБРАЗОВАНИЕМ В BCRYPT

//        if (Objects.nonNull(password) && !"".equalsIgnoreCase(password)) {
//            userDB.setPassword(password);
//        }

        if (Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            userDB.setName(name);
        }

        if (Objects.nonNull(surname) && !"".equalsIgnoreCase(surname)) {
            userDB.setSurname(surname);
        }

        userRepository.save(toUser(userDB));

        return userDB;
    }

    @Override
    public String deleteUserById(Long id) throws UserNotFoundException {

        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("There is no user with id: " + id);
        }

        userRepository.deleteById(id);

        return "successful deleted";
    }

    @Override
    public UserDTO updateUserStocks(Long userId, Long stockId) throws UserNotFoundException {

        List<Stock> stocks;
        UserDTO userDTO = getUserById(userId);

        Stock stock = stockRepository.findById(stockId).get();

        stocks = userDTO.getStocks();
        stocks.add(stock);

        userDTO.setStocks(stocks);

        userRepository.save(toUser(userDTO));

        return userDTO;
    }

    @Override
    public UserDTO deleteUserStocks(Long userId, Long stockId) throws UserNotFoundException {

        List<Stock> stocks;
        UserDTO userDTO = getUserById(userId);

        Stock stock = stockRepository.findById(stockId).get();

        stocks = userDTO.getStocks();
        stocks.remove(stock);

        userDTO.setStocks(stocks);

        userRepository.save(toUser(userDTO));

        return userDTO;

    }

    @Override
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

    @Override
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
