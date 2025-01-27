package tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monthly_limit")
public class MonthlyLimit extends BaseEntity {

    @Column(name = "limit_sum")
    private BigDecimal limitSum;

    @Column(name = "start_limit")
    private BigDecimal startLimit;

    @Column(name = "limit_datetime")
    private OffsetDateTime limitDateTime;

    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname;

}
