package com.kishko.userservice.dtos;

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
