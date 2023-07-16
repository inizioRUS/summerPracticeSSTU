package com.kishko.userservice.repositories;

import com.kishko.userservice.entities.AdvancedStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

public interface AdvancedStockRepository extends JpaRepository<AdvancedStock, Long> {

    List<AdvancedStock> findAdvancedStocksByUserId(Long user_id);

}
