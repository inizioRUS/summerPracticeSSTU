package com.ryasnov.transactionservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kishko.userservice.entities.AdvancedStock;
import com.kishko.userservice.entities.Stock;
import com.kishko.userservice.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "transactions")
//@EntityScan(basePackageClasses = {AdvancedStock.class, Transaction.class})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private TypeTransaction type;
    @ManyToOne
    @JoinColumn(name = "advanced_stock_id")
    private AdvancedStock stock;

    public Transaction(TypeTransaction type, AdvancedStock stock) {
        this.type = type;
        this.stock = stock;
    }

    public Transaction(TypeTransaction type) {
        this.type = type;
    }
}