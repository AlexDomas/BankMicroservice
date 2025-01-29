package tech.idftechnology.domas.bankmicroservice.bankmicroservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.CurrencyInformationUnavailableException;

@Slf4j
@RestControllerAdvice
public class CurrencyRestControllerAdvice {

    @ExceptionHandler(CurrencyInformationUnavailableException.class)
    public ResponseEntity<String> handleCurrencyInformationUnavailableException(final CurrencyInformationUnavailableException e) {
        log.error("An exception occurred! ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
