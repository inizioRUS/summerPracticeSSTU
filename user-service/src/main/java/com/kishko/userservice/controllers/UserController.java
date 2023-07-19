package com.kishko.userservice.controllers;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Tag(name="Пользователь-контроллер", description="Методы для взаимодействия с пользователями")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(
            summary = "Получение всех пользователей"
    )
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение пользователя по его ID"
    )
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/email")
    @Operation(
            summary = "Получение пользователя по его Email"
    )
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление данных пользователя по его ID",
            description = "Предоставляется возможность обновить данные пользователя в формате JSON. Изменить можно email, password, name, surname."
    )
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserById(id, userDTO), HttpStatus.OK);
    }

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

    @PutMapping("/{userId}/stock/{stockId}/{count}")
    @Operation(
            summary = "Добавление акций юзеру при покупке",
            description = "Для реализации необходимо передать в URL ID пользователя, ID акции (Stock) и количество."
    )
    public ResponseEntity<UserDTO> updateUserStocks(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId, @PathVariable("count") Integer count) throws Exception {
        return new ResponseEntity<>(userService.updateUserStocks(userId, stockId, count), HttpStatus.OK);
    }

    @PutMapping("/{userId}/incBalance/{amount}")
    @Operation(
            summary = "Пополнение баланса при продаже акции(й) или при его пополнении.",
            description = "Для реализации необходимо передать в URL ID пользователя, и сумму."
    )
    public ResponseEntity<UserDTO> increaseUserBalance(@PathVariable("userId") Long userId, @PathVariable("amount") Double amount) throws Exception {
        return new ResponseEntity<>(userService.increaseUserBalance(userId, amount), HttpStatus.OK);
    }

    @PutMapping("/{userId}/decBalance/{amount}")
    @Operation(
            summary = "Уменьшение баланса при покупке акции(й) или при выводе средств.",
            description = "Для реализации необходимо передать в URL ID пользователя, и сумму."
    )
    public ResponseEntity<UserDTO> decreaseUserBalance(@PathVariable("userId") Long userId, @PathVariable("amount") Double amount) throws Exception {
        return new ResponseEntity<>(userService.decreaseUserBalance(userId, amount), HttpStatus.OK);
    }

    @PutMapping("/{userId}/wishlist/stock/{stockId}")
    @Operation(
            summary = "Добавление акции в список желаемого",
            description = "Для реализации необходимо передать в URL ID пользователя и ID акции (Stock)."
    )
    public ResponseEntity<UserDTO> addUserWishlistStock(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.addUserWishlistStock(userId, stockId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/wishlist/stock/{stockId}")
    @Operation(
            summary = "Удаление акции из списка желаемого",
            description = "Для реализации необходимо передать в URL ID пользователя и ID акции (Stock)."
    )
    public ResponseEntity<UserDTO> deleteUserWishlistStock(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId) throws Exception {
        return new ResponseEntity<>(userService.deleteUserWishlistStock(userId, stockId), HttpStatus.OK);
    }

    @PutMapping("/{userId}/change/photo")
    @Operation(
            summary = "Изменение фотографии пользователя",
            description = "Для реализации необходимо передать в URL ID пользователя и выбрать фотографию. Старая фотография при этом удаляется. "
    )
    public ResponseEntity<UserDTO> changeUserPhoto(@PathVariable("userId") Long userId, @RequestParam("photo") MultipartFile photo) throws Exception {
        return new ResponseEntity<>(userService.changeUserPhoto(userId, photo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление пользователя по ID"
    )
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

    @DeleteMapping("/{userId}/stock/{advancedStockId}/{count}")
    @Operation(
            summary = "Удаление акций юзера при продаже",
            description = "Для реализации необходимо передать в URL ID пользователя, ID акции (AdvancedStock) и количество."
    )
    public ResponseEntity<UserDTO> deleteUserStocks(@PathVariable("userId") Long userId, @PathVariable("advancedStockId") Long stockId, @PathVariable("count") Integer count) throws Exception {
        return new ResponseEntity<>(userService.deleteUserStocks(userId, stockId, count), HttpStatus.OK);
    }

    @GetMapping("/profit/user/{userId}")
    @Operation(
            summary = "Получить прибыль юзера по его ID"
    )
    public ResponseEntity<Double> getProfitByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getProfitByUserId(userId), HttpStatus.OK);
    }

}
