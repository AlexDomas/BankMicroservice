package tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
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

    @Column(name = "category")
    private String category;

    public MonthlyLimit(BigDecimal limitSum, BigDecimal startLimit, OffsetDateTime limitDateTime, String limitCurrencyShortname, String category) {
        this.limitSum = limitSum;
        this.startLimit = startLimit;
        this.limitDateTime = limitDateTime;
        this.limitCurrencyShortname = limitCurrencyShortname;
        this.category = category;
    }

}
