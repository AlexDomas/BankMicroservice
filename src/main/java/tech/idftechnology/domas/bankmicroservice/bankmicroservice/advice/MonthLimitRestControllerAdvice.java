package tech.idftechnology.domas.bankmicroservice.bankmicroservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.MonthlyLimitAlreadyExist;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.NoExceededLimitTransactionsException;

@Slf4j
@RestControllerAdvice
public class MonthLimitRestControllerAdvice {

    @ExceptionHandler(MonthlyLimitAlreadyExist.class)
    public ResponseEntity<String> handleMonthlyLimitAlreadyExist(final MonthlyLimitAlreadyExist e) {
        log.error("An exception occurred! ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
