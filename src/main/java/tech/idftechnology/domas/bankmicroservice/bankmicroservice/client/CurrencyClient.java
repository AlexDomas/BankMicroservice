package tech.idftechnology.domas.bankmicroservice.bankmicroservice.client;

import java.math.BigDecimal;

public interface CurrencyClient {

    BigDecimal convertCurrencyKZTToUSD(BigDecimal KZTAmount);

    BigDecimal convertCurrencyRUBToUSD(BigDecimal RUBAmount);

}
