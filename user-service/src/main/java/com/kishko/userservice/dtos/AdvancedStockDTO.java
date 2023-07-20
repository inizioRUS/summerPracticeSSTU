package com.kishko.userservice.dtos;

import com.ryasnov.transactionservice.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancedStockDTO {

    private Long id;

    private Long stockId;

    private Long userId;

    private Integer count;

    private Double buyPrice;

    private List<Transaction> transactions;

}
