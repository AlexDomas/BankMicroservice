package tech.idftechnology.domas.bankmicroservice.bankmicroservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.client.impl.CurrencyClientImpl;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.MonthlyLimit;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.entity.Transaction;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.mapper.TransactionMapper;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.MonthlyLimitRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.repository.TransactionRepository;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.impl.TransactionServiceImpl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@ActiveProfiles("test")
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private MonthlyLimitRepository limitRepository;

    @Mock
    private CurrencyClientImpl currencyClient;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void createTransactionWithExceededLimit() {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(32131312321L, 2314124413L, new BigDecimal("1000"), "RUB", "services");
        MonthlyLimit monthlyLimit = new MonthlyLimit();
        monthlyLimit.setLimitSum(BigDecimal.valueOf(1000));

        Transaction transaction = transactionService.mapToTransaction(transactionRequestDTO);
        transaction.setLimitExceeded(false);

        when(limitRepository.findByLimitSumGreaterThanAndCategory(any(BigDecimal.class), eq("services"))).thenReturn(Optional.of(monthlyLimit));
        when(currencyClient.convertCurrencyRUBToUSD(any(BigDecimal.class))).thenReturn(BigDecimal.valueOf(50));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Long transactionId = transactionService.createTransaction(transactionRequestDTO);

        verify(limitRepository, times(1)).findByLimitSumGreaterThanAndCategory(any(BigDecimal.class), eq("services"));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        assertEquals(transaction.getId(), transactionId);
        assertFalse(transaction.getLimitExceeded());
    }
}
