package tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static tech.idftechnology.domas.bankmicroservice.bankmicroservice.constant.MessageErrorTransactionRequestDTO.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    @NotNull(message = MESSAGE_ERROR_ACCOUNT_FIELD_NOT_NULL)
    @Digits(integer = 15, fraction = 0, message = MESSAGE_ERROR_ACCOUNT_INCORRECT_AMOUNT_OF_NUMBERS)
    private Long accountFrom;

    @NotNull(message = MESSAGE_ERROR_ACCOUNT_FIELD_NOT_NULL)
    @Digits(integer = 15, fraction = 0, message = MESSAGE_ERROR_ACCOUNT_INCORRECT_AMOUNT_OF_NUMBERS)
    private Long accountTo;

    @NotNull(message = MESSAGE_ERROR_AMOUNT_FIELD_NOT_NULL)
    @Positive(message = MESSAGE_ERROR_AMOUNT_FIELD_NOT_POSITIVE)
    private BigDecimal amount;

    @NotNull(message = MESSAGE_ERROR_CURRENCY_FIELD_NOT_NULL)
    @NotBlank(message = MESSAGE_ERROR_CURRENCY_FIELD_NOT_BLANK)
    @Pattern(regexp = "^(RUB|KZT)$", message = MESSAGE_ERROR_INCORRECT_VALUE_OF_CURRENCY)
    private String currency;

    @NotNull(message = MESSAGE_ERROR_CATEGORY_FIELD_NOT_NULL)
    @NotBlank(message = MESSAGE_ERROR_CATEGORY_FIELD_NOT_BLANK)
    @Pattern(regexp = "^(goods|services)$", message = MESSAGE_ERROR_INCORRECT_VALUE_OF_CATEGORY)
    private String category;

}
