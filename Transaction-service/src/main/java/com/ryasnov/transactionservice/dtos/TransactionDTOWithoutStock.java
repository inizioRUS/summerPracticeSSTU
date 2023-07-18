package com.ryasnov.transactionservice.dtos;

import com.ryasnov.transactionservice.entities.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTOWithoutStock {
    private Long id;
    private TypeTransaction type;
}
