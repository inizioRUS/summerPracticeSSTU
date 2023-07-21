package com.ryasnov.transactionservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.ryasnov.transactionservice.dtos.TransactionDTO;
import com.ryasnov.transactionservice.dtos.TransactionDTOWithoutStock;
import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.entities.TypeTransaction;
import com.ryasnov.transactionservice.errors.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {

    Transaction toTransaction(TransactionDTO transactionDTO);
    TransactionDTO toDTO(Transaction transaction);
    Transaction toTransaction(TransactionDTOWithoutStock toDtoWithoutStock);
    TransactionDTOWithoutStock toDtoWithoutStock(Transaction transaction);
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTOWithoutStock createTransaction(TransactionDTOWithoutStock transactionDTOWithoutStock);
    List<TransactionDTO> getAllTransactions() throws TransactionNotFoundException;
//    List<TransactionDTO> getAllByUser(User user) throws TransactionNotFoundException;
    List<TransactionDTO> getAllByStock(AdvancedStock stock) throws TransactionNotFoundException;
    List<TransactionDTO> getAllByType(TypeTransaction type) throws TransactionNotFoundException;
    TransactionDTO updateTransactionById(Long id, Transaction transaction) throws TransactionNotFoundException;
    String deleteTransactionById(Long id) throws TransactionNotFoundException;

    TransactionDTO buyingShare(Long userId, Long stockId) throws Exception;
    TransactionDTO sellingShare(Long userId, AdvancedStock stock, Long stockId, Double price) throws Exception;
    TransactionDTO addFavourites(User user, Stock stock) throws UserNotFoundException;
    TransactionDTO deleteFavourites(User user, Stock stock) throws Exception;
    TransactionDTOWithoutStock withdrawal(User user, Double total) throws Exception;
    TransactionDTOWithoutStock addOnAccount(User user, Double total) throws UserNotFoundException;

}