package tech.idftechnology.domas.bankmicroservice.bankmicroservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.MonthlyLimitAlreadyExistException;

@Slf4j
@RestControllerAdvice
public class MonthLimitRestControllerAdvice {

    @ExceptionHandler(MonthlyLimitAlreadyExistException.class)
    public ResponseEntity<String> handleMonthlyLimitAlreadyExist(final MonthlyLimitAlreadyExistException e) {
        log.error("An exception occurred! ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
