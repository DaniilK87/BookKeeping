package com.example.bookkeeping;

import com.example.bookkeeping.dto.RatingResponseDto;
import com.example.bookkeeping.handling_exception.NoSuchBalanceException;
import com.example.bookkeeping.handling_exception.TransferMoneyException;
import com.example.bookkeeping.repo.GrantRepository;
import com.example.bookkeeping.service.GrantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GrantServiceTest {

    @Autowired
    private GrantService grantService;

    @MockBean
    private GrantRepository repository;

    @MockBean
    RatingResponseDto rating;

    @Test
    public void notBalance() {
        when(repository.getReferenceById(anyInt())).thenThrow(NoSuchBalanceException.class);
        assertThrows(NoSuchBalanceException.class, () -> grantService.getBalanceById(2));
    }

}