package tech.idftechnology.domas.bankmicroservice.bankmicroservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionExceededLimitResponseDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.TransactionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionRestController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/limit-exceeded")
    public ResponseEntity<TransactionExceededLimitResponseDTO> getAccExceededLimitTransactions(){
        return ResponseEntity.ok(transactionService.getExceededLimitTransactions());
    }

    @PostMapping
    public ResponseEntity<Long> createTransaction(@Valid @RequestBody TransactionRequestDTO transactionReq){
        Long id = transactionService.createTransaction(transactionReq);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
