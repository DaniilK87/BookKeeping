package com.example.bookkeeping;

import com.example.bookkeeping.controller.OperationController;
import com.example.bookkeeping.dto.StudentGrantDto;
import com.example.bookkeeping.entity.TransactionType;
import com.example.bookkeeping.service.OperationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {OperationController.class})
@ActiveProfiles("test")
public class OperationControllerTest {

    @MockBean
    private OperationService operationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnStatusOk() throws Exception {
        StudentGrantDto dto = new StudentGrantDto();
        dto.setSum(1000);
        dto.setDate("01-01-2001");
        dto.setType(TransactionType.GRANT.getType());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("http://localhost:8087/api/grants/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(post("http://localhost:8087/api/salary/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
