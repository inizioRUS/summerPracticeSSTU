package com.ryasnov.transactionservice.controllers;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.services.UserService;
import com.ryasnov.transactionservice.dtos.TransactionDTO;
import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.entities.TypeTransaction;
import com.ryasnov.transactionservice.errors.TransactionNotFoundException;
import com.ryasnov.transactionservice.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name="Контроллер транзакций", description="Методы для взаимодействия с транзакциями")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Operation(
            summary = "Создание транзации"
    )
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.createTransaction(transactionService.toDTO(transaction)), HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(
            summary = "Получение транзакций"
    )
    public ResponseEntity<List<TransactionDTO>> gelAllTransactions() throws TransactionNotFoundException {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }
    @GetMapping("/stock")
    @Transactional
    @Operation(
            summary = "Получение транзакции по акции",
            description = "Для реализации необходимо передать надстройку над акциями с пользователем и количество акций"
    )
    public ResponseEntity<List<TransactionDTO>> getAllByStock(@RequestBody AdvancedStock stock) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.getAllByStock(stock), HttpStatus.OK);
    }
    @GetMapping("/type")
    @Transactional
    @Operation(
            summary = "Получение транзакции по типу",
            description = "Для реализации необходимо передать тип транзации"
    )
    public ResponseEntity<List<TransactionDTO>> getAllByType(@RequestParam("type") TypeTransaction type) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.getAllByType(type), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Изменеие транзакции",
            description = "Для реализации необходимо передать в URL id транзакции и новую транзакцию, на основе которой будут проведены изменения"
    )
    ResponseEntity<TransactionDTO> updateTransactionById(@PathVariable("id") Long id, @RequestBody  Transaction transaction) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.updateTransactionById(id, transaction), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление транзакции по ее id",
            description = "Для реализации необходимо в URL передать id транзации"
    )
    ResponseEntity<String> deleteTransactionById(@PathVariable("id") Long id) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.deleteTransactionById(id), HttpStatus.OK);
    }
    @PutMapping("/buy")
    @Operation(
            summary = "Покупка транзакций",
            description = "Для реализации необходимо передать надстроку над множеством одинаковых транзаций"
    )
    ResponseEntity<UserDTO> buyingShare(@RequestBody AdvancedStock stock) throws Exception{
        return new ResponseEntity<>(transactionService.buyingShare(stock), HttpStatus.OK);
    }
    @PutMapping("/sell")
    @Operation(
            summary = "Продажа транзакций",
            description = "Для реализации необходимо передать надстроку над множеством одинаковых транзаций"
    )
    ResponseEntity<UserDTO> sellingShare(@RequestBody AdvancedStock stock) throws Exception{
        return new ResponseEntity<>(transactionService.sellingShare(stock), HttpStatus.OK);
    }
    @PutMapping("/favourites/add")
    @Operation(
            summary = "Добавление транзакции в любимое/отслеживаемое",
            description = "Для реализации необходимо передать саму транзакцию и пользователя, что добавил ее"
    )
    ResponseEntity<UserDTO> addFavourites(@RequestBody User user,@RequestBody Stock stock) throws UserNotFoundException{
        return new ResponseEntity<>(transactionService.addFavourites(user, stock), HttpStatus.OK);
    }
    @PutMapping("/favourites/del")
    @Operation(
            summary = "Удаление транзакции из любимое/отслеживаемое",
            description = "Для реализации необходимо передать саму транзакцию и пользователя, что добавил ее"
    )
    ResponseEntity<UserDTO> deleteFavourites(@RequestBody User user,@RequestBody Stock stock) throws Exception{
        return new ResponseEntity<>(transactionService.deleteFavourites(user, stock), HttpStatus.OK);
    }
    @PutMapping("/balance/decrease")
    @Operation(
            summary = "Вывод средств со счета",
            description = "Для реализации необходимо передать пользователя и сумму, которую он собирается вывести"
    )
    ResponseEntity<UserDTO> withdrawal(@RequestBody User user,@RequestParam("total") Double total) throws Exception{
        return new ResponseEntity<>(transactionService.withdrawal(user, total), HttpStatus.OK);
    }
    @PutMapping("/balance/add")
    @Operation(
            summary = "Добавление средств на счет",
            description = "Для реализации необходимо передать пользователя и сумму, которую он собирается внести"
    )
    ResponseEntity<UserDTO> addOnAccount(@RequestBody User user,@RequestParam("total") Double total) throws UserNotFoundException{
        return new ResponseEntity<>(transactionService.addOnAccount(user, total), HttpStatus.OK);
    }
}
