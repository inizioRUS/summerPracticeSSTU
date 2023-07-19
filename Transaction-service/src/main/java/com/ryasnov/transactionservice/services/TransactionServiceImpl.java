package com.ryasnov.transactionservice.services;

import com.kishko.userservice.dtos.UserDTO;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.errors.UserNotFoundException;
import com.kishko.userservice.repositories.AdvancedStockRepository;
import com.kishko.userservice.repositories.UserRepository;
import com.kishko.userservice.services.UserService;
import com.ryasnov.transactionservice.dtos.TransactionDTO;
import com.ryasnov.transactionservice.dtos.TransactionDTOWithoutStock;
import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.entities.TypeTransaction;
import com.ryasnov.transactionservice.errors.TransactionNotFoundException;
import com.ryasnov.transactionservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.ryasnov.transactionservice.entities.TypeTransaction.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;
    @Autowired
    AdvancedStockRepository advancedStockRepository;
    @Autowired
    UserRepository userRepository;

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
    public Transaction toTransaction(TransactionDTOWithoutStock toDtoWithoutStock) {
        return Transaction.builder()
                .id(toDtoWithoutStock.getId())
                .type(toDtoWithoutStock.getType())
                .build();
    }

    @Override
    public TransactionDTOWithoutStock toDtoWithoutStock(Transaction transaction) {
        return TransactionDTOWithoutStock.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .build();
    }


    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        transactionRepository.save(toTransaction(transactionDTO));
        return transactionDTO;
    }

    @Override
    public TransactionDTOWithoutStock createTransaction(TransactionDTOWithoutStock transactionDTOWithoutStock) {
        transactionRepository.save(toTransaction(transactionDTOWithoutStock));
        return transactionDTOWithoutStock;
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
    public TransactionDTO buyingShare(AdvancedStock stock) throws Exception {
        Transaction transaction = new Transaction(PURCHASE, stock);
        createTransaction(toDTO(transaction));
        userService.decreaseUserBalance(stock.getUser().getId(), stock.getCount()* stock.getBuyPrice());
        userService.updateUserStocks(stock.getUser().getId(), stock.getStock().getId(), stock.getCount());
        return toDTO(transaction);

    }

    @Override
    public TransactionDTO sellingShare(AdvancedStock stock) throws Exception {
        Transaction transaction = new Transaction(SALE, stock);
        createTransaction(toDTO(transaction));
        userService.increaseUserBalance(stock.getUser().getId(), stock.getCount()*stock.getStock().getPrice());
        userService.deleteUserStocks(stock.getUser().getId(), stock.getStock().getId(), stock.getCount());
        return toDTO(transaction);
       // return userService.toUser(userDTO);
    }

    @Override
    public TransactionDTO addFavourites(User user, Stock stock) throws UserNotFoundException {
        Transaction transaction = new Transaction(ADD_TO_SUBSCRIPTIONS, new AdvancedStock(stock,user,1));
        createTransaction(toDTO(transaction));
        userService.addUserWishlistStock(user.getId(), stock.getId());
        return toDTO(transaction);

    }

    @Override
    public TransactionDTO deleteFavourites(User user, Stock stock) throws Exception {
        Transaction transaction = new Transaction(REMOVING_FROM_SUBSCRIPTIONS, new AdvancedStock(stock,user,1));
        createTransaction(toDTO(transaction));
        userService.deleteUserWishlistStock(user.getId(), stock.getId());
        return toDTO(transaction);
    }

    @Override
    public TransactionDTOWithoutStock withdrawal(User user, Double total) throws Exception {
        Transaction transaction = new Transaction(WITHDRAWAL);
        createTransaction(toDtoWithoutStock(transaction));
        Long id = userService.getUserById(user.getId()).getId();
        userRepository.save(userService.toUser(userService.decreaseUserBalance(id, total)));
        return toDtoWithoutStock(transaction);

    }

    @Override
    public TransactionDTOWithoutStock addOnAccount(User user, Double total) throws UserNotFoundException {
        Transaction transaction = new Transaction( PUT_ON_THE_ACCOUNT);
        createTransaction(toDtoWithoutStock(transaction));
        Long id = userService.getUserById(user.getId()).getId();

        return toDtoWithoutStock(transaction);

    }
}
