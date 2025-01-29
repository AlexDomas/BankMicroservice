package tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception;

public class MonthlyLimitAlreadyExistException extends RuntimeException {

    public MonthlyLimitAlreadyExistException(String message) {
        super(message);
    }

}
