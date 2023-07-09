package java.com.ryasnov.transactionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.com.ryasnov.transactionservice.entities.Stock;
import java.com.ryasnov.transactionservice.entities.Transaction;
import java.com.ryasnov.transactionservice.entities.TypeTransaction;
import java.com.ryasnov.transactionservice.entities.User;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);
    List<Transaction> findByStock(Stock stock);
    List<Transaction> findByType(TypeTransaction type);
}
