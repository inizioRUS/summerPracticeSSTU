package com.kishko.userservice.repositories;

import com.kishko.userservice.entities.AdvancedStock;
import org.springframework.data.jpa.repository.JpaRepository;

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

public interface AdvancedStockRepository extends JpaRepository<AdvancedStock, Long> {

    AdvancedStock getAdvancedStockByUserIdAndStockId(Long user_id, Long stock_id);

}
