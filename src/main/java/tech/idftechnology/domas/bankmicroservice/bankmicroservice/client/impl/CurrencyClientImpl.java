package tech.idftechnology.domas.bankmicroservice.bankmicroservice.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.client.CurrencyClient;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.config.ApiProperties;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.CurrencyResponseDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.CurrencyRate;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.CurrencyInformationUnavailableException;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.CurrencyRateRepository;

import static tech.idftechnology.domas.bankmicroservice.bankmicroservice.constant.CurrencyConstant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CurrencyClientImpl implements CurrencyClient {

    private final RestTemplate restTemplate;

    private final ApiProperties apiProperties;

    private final CurrencyRateRepository currencyRepository;

    @Override
    public BigDecimal convertCurrencyKZTToUSD(BigDecimal KZTAmount) {
        return convertCurrency(KZTAmount, CONSTANT_CURRENCY_USD_KZT);
    }

    @Override
    public BigDecimal convertCurrencyRUBToUSD(BigDecimal RUBAmount) {
        return convertCurrency(RUBAmount, CONSTANT_CURRENCY_USD_RUB);
    }

    private BigDecimal convertCurrency(BigDecimal amount, String currencyPair) {
        BigDecimal exchangeRate = getExchangeRateFor(currencyPair);
        return amount.divide(exchangeRate, RoundingMode.DOWN);
    }

    private BigDecimal getExchangeRateFor(String currencyPair) {
        return currencyRepository.findByRateDateAndCurrency(LocalDate.now(), currencyPair)
                .map(CurrencyRate::getClose)
                .orElseGet(() -> fetchAndStoreExchangeRate(currencyPair));
    }

    private BigDecimal fetchAndStoreExchangeRate(String currencyPair) {
        BigDecimal exchangeRate = fetchExchangeRateWithFallback(currencyPair);
        saveExchangeRate(currencyPair, exchangeRate);
        return exchangeRate;
    }

    private void saveExchangeRate(String currencyPair, BigDecimal exchangeRate) {
        CurrencyRate currencyRate = new CurrencyRate(LocalDate.now(), currencyPair, exchangeRate);
        currencyRepository.save(currencyRate);
    }

    private BigDecimal fetchExchangeRateWithFallback(String currencyPair) {
        try {
            return fetchExchangeRate(currencyPair);
        } catch (RuntimeException e) {
            System.out.println("Current information about currency rate unavailable, using previous close!");
            return fetchPreviousClose(currencyPair);
        }
    }

    private BigDecimal fetchExchangeRate(String currencyPair) {
        CurrencyResponseDTO response = getCurrencyData(currencyPair);
        return extractCloseRate(response);
    }

    private CurrencyResponseDTO getCurrencyData(String currencyPair) {
        URI uri = buildCurrencyApiUri(currencyPair);
        return restTemplate.getForObject(uri, CurrencyResponseDTO.class);
    }

    private URI buildCurrencyApiUri(String currencyPair) {
        return UriComponentsBuilder.fromHttpUrl(apiProperties.getBaseApiUrl())
                .queryParam("symbol", currencyPair)
                .queryParam("interval", "1day")
                .queryParam("apikey", apiProperties.getApiKey())
                .queryParam("outputsize", "2")
                .build()
                .toUri();
    }

    private BigDecimal extractCloseRate(CurrencyResponseDTO response) {
        if (response != null && response.getValues() != null && !response.getValues().isEmpty()) {
            return response.getValues().get(0).getClose();
        }
        throw new CurrencyInformationUnavailableException("Unable to retrieve exchange currency rate!");
    }

    private BigDecimal fetchPreviousClose(String currencyPair) {
        CurrencyResponseDTO response = getCurrencyData(currencyPair);
        if (response != null && response.getValues() != null && response.getValues().size() > 1) {
            return response.getValues().get(1).getClose();
        }
        throw new CurrencyInformationUnavailableException("Unable to retrieve previous close for: " + currencyPair);
    }
}