package com.example.bookkeeping;

import com.example.bookkeeping.dto.BalanceDto;
import com.example.bookkeeping.entity.Balance;
import com.example.bookkeeping.entity.Operation;
import com.example.bookkeeping.entity.TransactionType;
import com.example.bookkeeping.repo.BalanceRepository;
import com.example.bookkeeping.service.BalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {
    @InjectMocks
    private BalanceServiceImpl balanceService;
    @Mock
    private BalanceRepository balanceRepository;

    @Test
    void create() {
        Balance balance = new Balance();
        Operation operation1 = Mockito.mock(Operation.class);
        Operation operation2 = Mockito.mock(Operation.class);
        List<Operation> list = new ArrayList<>();
        list.add(operation1);
        list.add(operation2);

        balance.setId(1);
        balance.setBalance(1000);
        balance.setTransactionType(TransactionType.GRANT);
        balance.setGrants(list);
        balanceRepository.save(balance);

        assertNotNull(balance);
        verify(balanceRepository, times(1)).save(balance);
    }

    @Test
    void update() {
        Operation operation1 = Mockito.mock(Operation.class);
        Operation operation2 = Mockito.mock(Operation.class);
        List<Operation> list = new ArrayList<>();
        list.add(operation1);
        list.add(operation2);
        Balance balance = new Balance();
        balance.setBalance(1);
        balance.setBalance(2000);
        balance.setTransactionType(TransactionType.SALARY);
        balance.setGrants(list);
        balanceRepository.save(balance);
        BalanceDto update = new BalanceDto();
        update.setBalance(1000);

        when(balanceRepository.getReferenceById(balance.getId())).thenReturn(balance);
        balanceService.changeBalance(update,balance.getId());

        assertNotNull(balance);
        assertNotEquals(balance.getBalance(), update.getBalance());
        verify(balanceRepository, times(1)).getReferenceById(balance.getId());
        verify(balanceRepository, times(2)).save(balance);
    }

    @Test
    void getById() {
        BalanceDto dto = Mockito.mock(BalanceDto.class);
        dto.setId(1);
        Optional<Balance> balance = balanceRepository.findById(dto.getId());
        assertNotNull(balance);
        verify(balanceRepository, times(1)).findById(dto.getId());
    }
}
