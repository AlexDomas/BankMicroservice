package tech.idftechnology.domas.bankmicroservice.bankmicroservice.constant;

public class MessageErrorTransactionRequestDTO {

    public static final String MESSAGE_ERROR_ACCOUNT_FIELD_NOT_NULL = "The account field cannot be null";
    public static final String MESSAGE_ERROR_CURRENCY_FIELD_NOT_NULL = "The currency field cannot be null";
    public static final String MESSAGE_ERROR_CATEGORY_FIELD_NOT_NULL = "The category field cannot be null";
    public static final String MESSAGE_ERROR_CATEGORY_FIELD_NOT_BLANK = "The category field cannot be blank";
    public static final String MESSAGE_ERROR_CURRENCY_FIELD_NOT_BLANK = "The currency field cannot be blank";
    public static final String MESSAGE_ERROR_ACCOUNT_INCORRECT_AMOUNT_OF_NUMBERS = "The account numbers must consist of 15 digits";
    public static final String MESSAGE_ERROR_AMOUNT_FIELD_NOT_NULL = "Amount field cannot be null";
    public static final String MESSAGE_ERROR_AMOUNT_FIELD_NOT_POSITIVE = "Amount field cannot be null";
    public static final String MESSAGE_ERROR_INCORRECT_VALUE_OF_CURRENCY = "Available currencies (RUB or KZT)";
    public static final String MESSAGE_ERROR_INCORRECT_VALUE_OF_CATEGORY = "Available categories (goods or services)";
}
