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
    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        transactionRepository.save(toTransaction(transactionDTO));
        return transactionDTO;
    }
    @Override
    public List<TransactionDTO> getAllTransactions() throws TransactionNotFoundException {
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
    public TransactionDTO updateTransactionById(Long id, Transaction transaction) throws TransactionNotFoundException {
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
        return toDTO(tmp);
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
    public UserDTO buyingShare(AdvancedStock stock) throws Exception {
        userService.decreaseUserBalance(stock.getUser().getId(), stock.getCount()*stock.getStock().getPrice());
        return userService.updateUserStocks(stock.getUser().getId(), stock.getStock().getId(), stock.getCount());

    }

    @Override
    public UserDTO sellingShare(AdvancedStock stock) throws Exception {
        userService.increaseUserBalance(stock.getUser().getId(), stock.getCount()*stock.getStock().getPrice());
        return userService.deleteUserStocks(stock.getUser().getId(), stock.getStock().getId(), stock.getCount());
       // return userService.toUser(userDTO);
    }

    @Override
    public UserDTO addFavourites(User user, Stock stock) throws UserNotFoundException {
        return userService.addUserWishlistStock(user.getId(), stock.getId());

    }

    @Override
    public UserDTO deleteFavourites(User user, Stock stock) throws Exception {
        return userService.deleteUserWishlistStock(user.getId(), stock.getId());

    }

    @Override
    public UserDTO withdrawal(User user, Double total) throws Exception {
        return userService.decreaseUserBalance(user.getId(), total);

    }

    @Override
    public UserDTO addOnAccount(User user, Double total) throws UserNotFoundException {
        return userService.increaseUserBalance(user.getId(), total);

    }
}
