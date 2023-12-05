package com.example.bookkeeping.service;

import com.example.bookkeeping.dto.BalanceDto;
import com.example.bookkeeping.entity.Balance;
import com.example.bookkeeping.handling_exception.NoSuchBalanceException;
import com.example.bookkeeping.repo.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService{
    private final BalanceRepository repository;
    @Override
    public void changeBalance(BalanceDto balanceDto, Integer balanceId) {
        Balance balance = repository.getReferenceById(balanceId);
        if (balance.getBalance() == null) {
            balance.setBalance(balanceDto.getBalance());
        } else {
            balance.setBalance(balance.getBalance() + balanceDto.getBalance());
            log.info("Счет пополнен на : " + balanceDto.getBalance());
        }
        balance.setTransactionType(balanceDto.getType());
        repository.save(balance);
    }
    @Override
    public void createBalance(BalanceDto balanceDto) {
        Balance balance = new Balance();
        balance.setBalance(balanceDto.getBalance());
        balance.setTransactionType(balanceDto.getType());
        repository.save(balance);
        log.info("Счет id: " + balance.getId() +  "успешно создан");
    }
    @Override
    public BalanceDto getBalanceById(Integer balanceId) {
        Balance balance = repository.findById(balanceId).orElseThrow(
                () -> new NoSuchBalanceException("Счет с  id: " + balanceId + " не найден"));
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setId(balance.getId());
        balanceDto.setBalance(balance.getBalance());
        balanceDto.setType(balance.getTransactionType());
        return balanceDto;
    }
}
