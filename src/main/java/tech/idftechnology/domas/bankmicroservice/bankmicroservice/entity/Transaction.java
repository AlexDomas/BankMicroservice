package tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity{

    @Column(name = "account_from")
    private Long accountFrom;

    @Column(name = "account_to")
    private Long accountTo;

    @Column(name = "currency_shortname")
    private String currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "category")
    private String category;

    @Column(name = "transaction_date")
    private OffsetDateTime transactionDate;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    public Transaction(Long accountFrom, Long accountTo, String currency, BigDecimal amount, String category, OffsetDateTime transactionDate) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currency = currency;
        this.amount = amount;
        this.category = category;
        this.transactionDate = transactionDate;
    }
}
