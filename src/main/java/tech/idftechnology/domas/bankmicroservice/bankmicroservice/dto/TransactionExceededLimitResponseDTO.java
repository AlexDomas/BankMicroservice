package tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TransactionExceededLimitResponseDTO {

    private List<Transaction> limitExceededTransactions;

    private List<MonthlyLimit> limits;

}
