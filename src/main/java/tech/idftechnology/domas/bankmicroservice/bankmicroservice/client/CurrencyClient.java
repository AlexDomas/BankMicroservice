package tech.idftechnology.domas.bankmicroservice.bankmicroservice.client;

import java.math.BigDecimal;

public interface CurrencyClient {

    public BigDecimal convertCurrencyKZTToUSD(BigDecimal KZTAmount);

    public BigDecimal convertCurrencyRUBToUSD(BigDecimal RUBAmount);

}
