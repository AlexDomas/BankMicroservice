package tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception;

public class NoExceededLimitTransactionsException extends RuntimeException {

    public NoExceededLimitTransactionsException(String message) {
        super(message);
    }

}
