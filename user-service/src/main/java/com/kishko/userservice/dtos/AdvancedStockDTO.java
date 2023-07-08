package com.kishko.userservice.dtos;

import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
