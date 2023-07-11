package com.ryasnov.transactionservice.services;

import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
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

//    String buyingShare(User user, AdvancedStock stock);
//    String sellingShare(User user, AdvancedStock stock);
//    String addFavourites(User user, Stock stock);
//    String deleteFavourites(User user, Stock stock);
//    String withdrawal(User user, int total);
//    String addOnAccount(User user, int total);

}