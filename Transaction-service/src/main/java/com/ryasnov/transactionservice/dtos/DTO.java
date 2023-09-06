package com.ryasnov.transactionservice.dtos;

import com.ryasnov.transactionservice.entities.TypeTransaction;

public record DTO(
        Long id,
        TypeTransaction type,
        Long stockId
) { }
