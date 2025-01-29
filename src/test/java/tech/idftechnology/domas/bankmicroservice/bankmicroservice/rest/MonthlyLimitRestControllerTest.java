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
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.MonthlyLimitRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.dto.TransactionRequestDTO;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.exception.MonthlyLimitAlreadyExistException;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.MonthlyLimitService;
import tech.idftechnology.domas.bankmicroservice.bankmicroservice.service.TransactionService;

import javax.naming.LimitExceededException;
import java.math.BigDecimal;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@ActiveProfiles("test")
public class MonthlyLimitRestControllerTest {

    @SuppressWarnings(value = "all")
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MonthlyLimitService monthlyLimitService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createLimit_CheckingCreatingLimitWithExceededLimitTransaction_ShouldReturnCreatedStatus() throws Exception {
        MonthlyLimitRequestDTO monthlyLimitRequestDTO = new MonthlyLimitRequestDTO(new BigDecimal("1000.0"), "USD", "goods");
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(32131312321L, 2314124413L, new BigDecimal("100000000.00"), "RUB", "goods");
        monthlyLimitService.setMonthlyLimit(monthlyLimitRequestDTO);

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v2/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monthlyLimitRequestDTO)))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    public void createLimit_WhenLimitNotExceeded_ShouldThrowException() throws Exception {
        MonthlyLimitRequestDTO monthlyLimitRequestDTO = new MonthlyLimitRequestDTO(new BigDecimal("1000.0"), "USD", "goods");

        doThrow(new MonthlyLimitAlreadyExistException("Limit for category goods is not exceeded yet."))
                .when(monthlyLimitService).setMonthlyLimit(monthlyLimitRequestDTO);

        mockMvc.perform(post("/api/v2/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monthlyLimitRequestDTO)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Limit for category " + monthlyLimitRequestDTO.getCategory() + " is not exceeded yet.")));
    }

    @Test
    public void getMonthlyLimits_CheckingForGettingAllMonthlyLimits_ShouldReturnStatusOK() throws Exception {
        this.mockMvc.perform(get("/api/v2/limits"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
