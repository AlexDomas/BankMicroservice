package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.client.CurrencyClient;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionExceededLimitResponseDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.NoExceededLimitTransactionsException;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.NoMonthlyLimitsFoundException;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.MonthlyLimitRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.TransactionRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.TransactionService;

import static tech.idftechnology.domas.bankmicroservice.bankmicroservice.constant.CurrencyConstant.CONSTANT_CURRENCY_RUB;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CurrencyClient currencyClient;

    private final TransactionRepository transactionRepository;

    private final MonthlyLimitRepository monthlyLimitRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    @Override
    public TransactionExceededLimitResponseDTO getExceededLimitTransactions() {
        List<Transaction> limitExceededTransactions = transactionRepository.findTransactionsByLimitExceededTrue();
        if (limitExceededTransactions.isEmpty()) {
            throw new NoExceededLimitTransactionsException("No transactions with exceeded limit found.");
        }
        List<MonthlyLimit> limits = monthlyLimitRepository.findAll();
        if (limits.isEmpty()) {
            throw new NoMonthlyLimitsFoundException("No monthly limits found.");
        }
        return new TransactionExceededLimitResponseDTO(limitExceededTransactions, limits);
    }

    @Transactional
    @Override
    public Long createTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = mapToTransaction(transactionRequestDTO);
        String category = transactionRequestDTO.getCategory();
        Optional<MonthlyLimit> optionalLimit = monthlyLimitRepository.findByLimitSumGreaterThanAndCategory(BigDecimal.valueOf(0), category);
        if(optionalLimit.isPresent()){
            MonthlyLimit monthlyLimit = optionalLimit.get();
            BigDecimal usdTransactionAmount = convertTransactionAmount(transactionRequestDTO);
            updateLimitAndTransaction(monthlyLimit, usdTransactionAmount, transaction);
        }
        else{
            transaction.setLimitExceeded(true);
        }
        return transactionRepository.save(transaction).getId();
    }

    public Transaction mapToTransaction(TransactionRequestDTO transactionRequestDTO) {
        return new Transaction(
                transactionRequestDTO.getAccountFrom(),
                transactionRequestDTO.getAccountTo(),
                transactionRequestDTO.getCurrency(),
                transactionRequestDTO.getAmount(),
                transactionRequestDTO.getCategory(),
                OffsetDateTime.now(ZoneOffset.ofHours(3)));
    }

    private BigDecimal convertTransactionAmount(TransactionRequestDTO transactionRequestDTO) {
        return transactionRequestDTO.getCurrency().equals(CONSTANT_CURRENCY_RUB)
                ? currencyClient.convertCurrencyRUBToUSD(transactionRequestDTO.getAmount())
                : currencyClient.convertCurrencyKZTToUSD(transactionRequestDTO.getAmount());
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
