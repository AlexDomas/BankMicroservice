package tech.idftechnology.domas.bankmicroservice.bankmicroservice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.TransactionService;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@ActiveProfiles("test")
public class TransactionRestControllerTest {

    @SuppressWarnings(value = "all")
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createTransaction_CheckingCreatingTransaction_ShouldReturnCreatedStatus() throws Exception {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(32131312321L, 2314124413L, new BigDecimal("1000.00"), "RUB", "services");
        Long expectedId = 1L;
        when(transactionService.createTransaction(transactionRequestDTO)).thenReturn(expectedId);

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getTransactions_CheckingForGettingAllTransactions_ShouldReturnStatusOK() throws Exception {
        this.mockMvc.perform(get("/api/v1/transactions"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getLimitExceededTransactions_CheckingForGettingAllLimitExceededTransactions_ShouldReturnStatusOK() throws Exception {
        this.mockMvc.perform(get("/api/v1/transactions/limit-exceeded"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
