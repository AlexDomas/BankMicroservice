package tech.idftechnology.domas.bankmicroservice.bankmicroservice.mapper;

import org.springframework.stereotype.Component;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class TransactionMapper {

    public Transaction mapToTransaction(TransactionRequestDTO transactionRequestDTO) {
        return new Transaction(
                transactionRequestDTO.getAccountFrom(),
                transactionRequestDTO.getAccountTo(),
                transactionRequestDTO.getCurrency(),
                transactionRequestDTO.getAmount(),
                transactionRequestDTO.getCategory(),
                OffsetDateTime.now(ZoneOffset.ofHours(3)));
    }

}
