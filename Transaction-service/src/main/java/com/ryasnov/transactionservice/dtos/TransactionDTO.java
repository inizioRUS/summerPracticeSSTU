package com.ryasnov.transactionservice.dtos;

import com.ryasnov.transactionservice.entities.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private TypeTransaction type;
    private Long stockId;

}
