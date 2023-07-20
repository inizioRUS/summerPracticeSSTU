package com.kishko.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryasnov.transactionservice.entities.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

//    TODO НЕ МОЕ ОТДАТЬ ТОМУ КТО ДЕЛАЕТ STOCK

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "advancedStocks")
public class AdvancedStock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "count")
    private Integer count;

    @Column(name = "buyPrice")
    private Double buyPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public AdvancedStock(Stock stock, User user, Integer count) {
        this.stock = stock;
        this.user = user;
        this.count = count;
    }
}
