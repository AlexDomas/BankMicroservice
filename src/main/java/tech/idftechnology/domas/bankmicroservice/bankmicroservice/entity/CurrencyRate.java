package tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_rate")
public class CurrencyRate extends BaseEntity {

    @Column(name = "rate_date")
    private LocalDate rateDate;

    @Column(name = "currency_pair")
    private String currency;

    @Column(name = "close_rate")
    private BigDecimal close;
}
