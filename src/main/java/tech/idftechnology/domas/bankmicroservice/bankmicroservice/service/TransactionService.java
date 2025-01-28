package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service;

import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    Long createTransaction(TransactionRequestDTO transactionRequestDTO);

}
