package tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrencyResponseDTO {

    @JsonProperty("values")
    private List<CurrencyValueDTO> values;

}