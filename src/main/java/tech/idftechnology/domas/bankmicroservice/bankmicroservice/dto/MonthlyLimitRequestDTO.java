package tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

import static tech.idftechnology.domas.bankmicroservice.bankmicroservice.constant.MessageErrorMonthlyLimitRequestDTO.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyLimitRequestDTO {

    @Positive(message = MESSAGE_ERROR_INCORRECT_VALUE_OF_LIMIT_SUM)
    private BigDecimal limitSum;

    @NotNull(message = MESSAGE_ERROR_CURRENCY_FIELD_NOT_NULL)
    @NotBlank(message = MESSAGE_ERROR_CURRENCY_FIELD_NOT_BLANK)
    @Pattern(regexp = "^(USD)$", message = MESSAGE_ERROR_INCORRECT_CURRENCY_SHORTNAME)
    private String currencyShortname;

    @NotNull(message = MESSAGE_ERROR_CATEGORY_FIELD_NOT_NULL)
    @NotBlank(message = MESSAGE_ERROR_CATEGORY_FIELD_NOT_BLANK)
    @Pattern(regexp = "^(goods|services)$", message = MESSAGE_ERROR_INCORRECT_VALUE_OF_CATEGORY)
    private String category;

}