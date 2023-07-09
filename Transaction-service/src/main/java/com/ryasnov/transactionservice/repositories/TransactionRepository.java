package com.ryasnov.transactionservice.repositories;

import com.ryasnov.transactionservice.entities.Transaction;
import com.ryasnov.transactionservice.entities.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    List<Transaction> findByUser(User user);
//    List<Transaction> findByStock(Stock stock);
    List<Transaction> findByType(TypeTransaction type);
}
