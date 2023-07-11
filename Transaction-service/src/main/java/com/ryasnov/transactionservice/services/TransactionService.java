package com.ryasnov.transactionservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.ryasnov.transactionservice.dtos.TransactionDTO;
import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.entities.TypeTransaction;
import com.ryasnov.transactionservice.errors.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {

    Transaction toTransaction(TransactionDTO transactionDTO);
    TransactionDTO toDTO(Transaction transaction);
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getAllTransactions() throws TransactionNotFoundException;
//    List<TransactionDTO> getAllByUser(User user) throws TransactionNotFoundException;
    List<TransactionDTO> getAllByStock(AdvancedStock stock) throws TransactionNotFoundException;
    List<TransactionDTO> getAllByType(TypeTransaction type) throws TransactionNotFoundException;
    TransactionDTO updateTransactionById(Long id, Transaction transaction) throws TransactionNotFoundException;
    String deleteTransactionById(Long id) throws TransactionNotFoundException;

    UserDTO buyingShare(AdvancedStock stock) throws Exception;
    UserDTO sellingShare(AdvancedStock stock) throws Exception;
    UserDTO addFavourites(User user, Stock stock) throws UserNotFoundException;
    UserDTO deleteFavourites(User user, Stock stock) throws Exception;
    UserDTO withdrawal(User user, Double total) throws Exception;
    UserDTO addOnAccount(User user, Double total) throws UserNotFoundException;

}