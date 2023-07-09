package java.com.ryasnov.transactionservice.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.com.ryasnov.transactionservice.entities.Stock;
import java.com.ryasnov.transactionservice.entities.TypeTransaction;
import java.com.ryasnov.transactionservice.entities.User;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private TypeTransaction type;
    private User user;
    private Stock stock;

}
