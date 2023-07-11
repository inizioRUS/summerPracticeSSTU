package com.ryasnov.transactionservice.services;

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

    List<TransactionDTO> gelAllTransactions() throws TransactionNotFoundException;
//    List<TransactionDTO> getAllByUser(User user) throws TransactionNotFoundException;
    List<TransactionDTO> getAllByStock(AdvancedStock stock) throws TransactionNotFoundException;
    List<TransactionDTO> getAllByType(TypeTransaction type) throws TransactionNotFoundException;
    String updateTransactionById(Long id, Transaction transaction) throws TransactionNotFoundException;
    String deleteTransactionById(Long id) throws TransactionNotFoundException;

    User buyingShare(AdvancedStock stock) throws Exception;
    User sellingShare(AdvancedStock stock) throws Exception;
    User addFavourites(User user, Stock stock) throws UserNotFoundException;
    User deleteFavourites(User user, Stock stock) throws Exception;
    User withdrawal(User user, Double total) throws Exception;
    User addOnAccount(User user, Double total) throws UserNotFoundException;

}