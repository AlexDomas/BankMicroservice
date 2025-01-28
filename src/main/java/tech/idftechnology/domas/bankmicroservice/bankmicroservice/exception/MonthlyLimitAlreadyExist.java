package tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception;

public class MonthlyLimitAlreadyExist extends RuntimeException {

    public MonthlyLimitAlreadyExist(String message) {
        super(message);
    }
}
