package com.ryasnov.transactionservice.entities;

import com.kishko.userservice.entities.AdvancedStock;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "transactions")
//@EntityScan(basePackageClasses = {AdvancedStock.class, Transaction.class})
public class Transaction implements Serializable {
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

    public void setStock(AdvancedStock stock) {
        this.stock = stock;
    }

    public Transaction(TypeTransaction type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}