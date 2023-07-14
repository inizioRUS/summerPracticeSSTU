package com.kishko.userservice.repositories;

import com.kishko.userservice.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findStockByName(String name);

}
