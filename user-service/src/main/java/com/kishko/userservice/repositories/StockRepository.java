package com.kishko.userservice.repositories;

import com.kishko.userservice.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findStocksByUsersId(Long users_id);

}
