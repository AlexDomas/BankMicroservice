package tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception;

public class NoMonthlyLimitsFoundException extends RuntimeException {

    public NoMonthlyLimitsFoundException(String message) {
        super(message);
    }

}
