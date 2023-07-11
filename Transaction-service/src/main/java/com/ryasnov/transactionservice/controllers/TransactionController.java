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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.createTransaction(transactionService.toDTO(transaction)), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> gelAllTransactions() throws TransactionNotFoundException {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }
    @GetMapping("/stock")
    public ResponseEntity<List<TransactionDTO>> getAllByStock(@RequestBody AdvancedStock stock) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.getAllByStock(stock), HttpStatus.OK);
    }
    @GetMapping("/type")
    public ResponseEntity<List<TransactionDTO>> getAllByType(@RequestParam("type") TypeTransaction type) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.getAllByType(type), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    ResponseEntity<TransactionDTO> updateTransactionById(@PathVariable("id") Long id, @RequestBody  Transaction transaction) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.updateTransactionById(id, transaction), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTransactionById(@PathVariable("id") Long id) throws TransactionNotFoundException{
        return new ResponseEntity<>(transactionService.deleteTransactionById(id), HttpStatus.OK);
    }
    @PutMapping("/buy")
    ResponseEntity<UserDTO> buyingShare(@RequestBody AdvancedStock stock) throws Exception{
        return new ResponseEntity<>(transactionService.buyingShare(stock), HttpStatus.OK);
    }
    @PutMapping("/sell")
    ResponseEntity<UserDTO> sellingShare(@RequestBody AdvancedStock stock) throws Exception{
        return new ResponseEntity<>(transactionService.sellingShare(stock), HttpStatus.OK);
    }
    @PutMapping("/favourites/add")
    ResponseEntity<UserDTO> addFavourites(@RequestBody User user,@RequestBody Stock stock) throws UserNotFoundException{
        return new ResponseEntity<>(transactionService.addFavourites(user, stock), HttpStatus.OK);
    }
    @PutMapping("/favourites/del")
    ResponseEntity<UserDTO> deleteFavourites(@RequestBody User user,@RequestBody Stock stock) throws Exception{
        return new ResponseEntity<>(transactionService.deleteFavourites(user, stock), HttpStatus.OK);
    }
    @PutMapping("/balance/decrease")
    ResponseEntity<UserDTO> withdrawal(@RequestBody User user,@PathVariable("total") Double total) throws Exception{
        return new ResponseEntity<>(transactionService.withdrawal(user, total), HttpStatus.OK);
    }
    @PutMapping("/balance/add")
    ResponseEntity<UserDTO> addOnAccount(@RequestBody User user,@PathVariable("total") Double total) throws UserNotFoundException{
        return new ResponseEntity<>(transactionService.addOnAccount(user, total), HttpStatus.OK);
    }
}
