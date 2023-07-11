package com.ryasnov.transactionservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.AdvancedStockRepository;
import com.kishko.userservice.services.UserService;
import com.ryasnov.transactionservice.dtos.TransactionDTO;
import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.entities.TypeTransaction;
import com.ryasnov.transactionservice.errors.TransactionNotFoundException;
import com.ryasnov.transactionservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;
    @Autowired
    AdvancedStockRepository advancedStockRepository;

    @Override
    public Transaction toTransaction(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .stock(advancedStockRepository.findById(transactionDTO.getStockId()).get())
                .type(transactionDTO.getType())
                .build();
    }

    @Override
    public TransactionDTO toDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .stockId(transaction.getStock().getId())
                .type(transaction.getType())
                .build();
    }

    @Override
    public List<TransactionDTO> gelAllTransactions() throws TransactionNotFoundException {
        return transactionRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

//    @Override
//    public List<TransactionDTO> getAllByUser(User user) throws TransactionNotFoundException {
//        return transactionRepository.findByUser(user)
//                .stream()
//                .map(this::toDTO)
//                .toList();
//    }

    @Override
    public List<TransactionDTO> getAllByStock(AdvancedStock stock) throws TransactionNotFoundException {
        return transactionRepository.findByStock(stock)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<TransactionDTO> getAllByType(TypeTransaction type) throws TransactionNotFoundException {
        return transactionRepository.findByType(type)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public String updateTransactionById(Long id, Transaction transaction) throws TransactionNotFoundException {
        Optional<Transaction> optionalTmp = transactionRepository.findById(id);
        Transaction tmp = optionalTmp.get();
//        if(Objects.nonNull(transaction.getUser())){
//            tmp.setUser(transaction.getUser());
//        }
        if(Objects.nonNull(transaction.getStock())){
            tmp.setStock(transaction.getStock());
        }
        if(Objects.nonNull(transaction.getType())){
            tmp.setType(transaction.getType());
        }
        transactionRepository.save(tmp);
        return "Transaction has been changed";
    }

    @Override
    public String deleteTransactionById(Long id) throws TransactionNotFoundException {
        Optional<Transaction> transactionForDelete =transactionRepository.findById(id);
        if(transactionForDelete.isEmpty()){
            throw new TransactionNotFoundException("There is no such transaction");
        }
        transactionRepository.deleteById(id);
        return "Transaction has been deleted";
    }
    //Преверсия
    @Override
    public User buyingShare(User user, AdvancedStock stock) throws Exception {
        userService.decreaseUserBalance(user.getId(), stock.getCount()*stock.getStock().getPrice());
        UserDTO userDTO = userService.updateUserStocks(user.getId(), stock.getStock().getId(), stock.getCount());
        return userService.toUser(userDTO);
    }

    @Override
    public User sellingShare(User user, AdvancedStock stock) throws Exception {
        userService.increaseUserBalance(user.getId(), stock.getCount()*stock.getStock().getPrice());
        UserDTO userDTO = userService.deleteUserStocks(user.getId(), stock.getStock().getId(), stock.getCount());
        return userService.toUser(userDTO);
    }

//    @Override
//    public String addFavourites(User user, Stock stock) {
//        return null;
//    }
//
//    @Override
//    public String deleteFavourites(User user, Stock stock) {
//        return null;
//    }
//
//    @Override
//    public String withdrawal(User user, int total) {
//        return null;
//    }
//
//    @Override
//    public String addOnAccount(User user, int total) {
//        return null;
//    }
}
