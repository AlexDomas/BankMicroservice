package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service;

import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    public UUID createTransaction(TransactionRequestDTO transactionRequestDTO);

}
