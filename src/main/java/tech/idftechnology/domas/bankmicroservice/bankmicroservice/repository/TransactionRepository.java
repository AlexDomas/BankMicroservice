package tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findTransactionsByLimitExceededTrue();

}
