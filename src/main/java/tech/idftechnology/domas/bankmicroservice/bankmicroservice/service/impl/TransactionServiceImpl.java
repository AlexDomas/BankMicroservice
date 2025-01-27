package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.mapper.TransactionMapper;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.MonthlyLimitRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.TransactionRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;

    MonthlyLimitRepository monthlyLimitRepository;

    TransactionMapper transactionMapper;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public UUID createTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = transactionMapper.mapToTransaction(transactionRequestDTO);
        Optional<MonthlyLimit> optionalLimit = monthlyLimitRepository.findByLimitSumGreaterThan(BigDecimal.valueOf(0));
        if(optionalLimit.isPresent()){
            MonthlyLimit monthlyLimit = optionalLimit.get();
            BigDecimal usdTransactionAmount = BigDecimal.valueOf(0);/*convertTransactionAmountToUsd*/
            updateLimitAndTransaction(monthlyLimit, usdTransactionAmount, transaction);
        }
        return transactionRepository.save(transaction).getId();
    }

    private void updateLimitAndTransaction(MonthlyLimit monthlyLimit, BigDecimal usdTransactionAmount, Transaction transaction) {
        if(monthlyLimit.getLimitSum().compareTo(usdTransactionAmount) >= 0){
            monthlyLimit.setLimitSum(monthlyLimit.getLimitSum().subtract(usdTransactionAmount));
            transaction.setLimitExceeded(false);
        }
        else{
            monthlyLimit.setLimitSum(BigDecimal.valueOf(0));
            transaction.setLimitExceeded(true);
        }
        monthlyLimitRepository.save(monthlyLimit);
    }

}
