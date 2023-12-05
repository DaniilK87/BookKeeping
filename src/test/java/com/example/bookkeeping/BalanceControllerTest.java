package com.example.bookkeeping;

import com.example.bookkeeping.controller.BalanceController;
import com.example.bookkeeping.entity.Balance;
import com.example.bookkeeping.entity.TransactionType;
import com.example.bookkeeping.service.BalanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = {BalanceController.class})
@ActiveProfiles("test")
public class BalanceControllerTest {

    @MockBean
    private BalanceService balanceService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnStatusOk() throws Exception {
        Balance balance = new Balance();
        balance.setId(1);
        balance.setBalance(1000);
        balance.setTransactionType(TransactionType.GRANT);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(balance);

        mockMvc.perform(post("http://localhost:8087/api/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8087/api/balance/change/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(get("http://localhost:8087/api/balance/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}




