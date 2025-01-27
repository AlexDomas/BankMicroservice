package tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyValueDTO {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("close")
    private BigDecimal close;

}
