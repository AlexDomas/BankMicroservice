package tech.idftechnology.domas.bankmicroservice.bankmicroservice.constant;

public class MessageErrorMonthlyLimitRequestDTO {

    public static final String MESSAGE_ERROR_CURRENCY_FIELD_NOT_NULL = "The currency field cannot be null";
    public static final String MESSAGE_ERROR_CURRENCY_FIELD_NOT_BLANK = "The currency field cannot be blank";
    public static final String MESSAGE_ERROR_INCORRECT_CURRENCY_SHORTNAME = "You can set the amount of the transaction limit only in USD";
    public static final String MESSAGE_ERROR_INCORRECT_VALUE_OF_LIMIT_SUM = "Monthly limit must be positive";
    public static final String MESSAGE_ERROR_CATEGORY_FIELD_NOT_NULL = "The category field cannot be null";
    public static final String MESSAGE_ERROR_CATEGORY_FIELD_NOT_BLANK = "The category field cannot be blank";
    public static final String MESSAGE_ERROR_INCORRECT_VALUE_OF_CATEGORY = "Available categories (goods or services)";

}
