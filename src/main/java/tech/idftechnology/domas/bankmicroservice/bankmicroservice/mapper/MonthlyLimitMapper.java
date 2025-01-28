package tech.idftechnology.domas.bankmicroservice.bankmicroservice.mapper;

import org.springframework.stereotype.Component;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class MonthlyLimitMapper {

    public MonthlyLimit mapToMonthlyLimit(MonthlyLimitRequestDTO monthlyLimitRequestDTO) {
        return new MonthlyLimit(
                monthlyLimitRequestDTO.getLimitSum(),
                monthlyLimitRequestDTO.getLimitSum(),
                OffsetDateTime.now(ZoneOffset.ofHours(3)),
                monthlyLimitRequestDTO.getCurrencyShortname(),
                monthlyLimitRequestDTO.getCategory());
    }

}
