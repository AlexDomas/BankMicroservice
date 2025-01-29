package tech.idftechnology.domas.bankmicroservice.bankmicroservice.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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

@Api(description = "Endpoint which helps us to get information about transactions")
@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionRestController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Api for getting all transactions")
    @ApiResponse(code = 200, response = Transaction.class, message = "OK")
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @ApiOperation(value = "Api for getting all limit exceeded transactions")
    @ApiResponse(code = 200, response = TransactionExceededLimitResponseDTO.class, message = "OK")
    @GetMapping("/limit-exceeded")
    public ResponseEntity<TransactionExceededLimitResponseDTO> getAccExceededLimitTransactions(){
        return ResponseEntity.ok(transactionService.getExceededLimitTransactions());
    }

    @ApiOperation(value = "Create a new transaction", notes = "This API creates a new transaction and returns its ID.")
    @ApiResponse(code = 201, response = Long.class, message = "Transaction created successfully")
    @PostMapping
    public ResponseEntity<Long> createTransaction(@Valid @RequestBody TransactionRequestDTO transactionReq){
        return new ResponseEntity<>(transactionService.createTransaction(transactionReq), HttpStatus.CREATED);
    }

}
