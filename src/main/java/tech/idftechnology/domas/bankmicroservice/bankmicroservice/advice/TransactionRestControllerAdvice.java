package tech.idftechnology.domas.bankmicroservice.bankmicroservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.NoExceededLimitTransactionsException;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.NoMonthlyLimitsFoundException;

@Slf4j
@RestControllerAdvice
public class TransactionRestControllerAdvice {

    @ExceptionHandler(NoExceededLimitTransactionsException.class)
    public ResponseEntity<String> handleNoExceededLimitTransactionException(final NoExceededLimitTransactionsException e) {
        log.error("An exception occurred! ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoMonthlyLimitsFoundException.class)
    public ResponseEntity<String> handleNoMonthlyLimitsFoundException(final NoMonthlyLimitsFoundException e) {
        log.error("An exception occurred! ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
