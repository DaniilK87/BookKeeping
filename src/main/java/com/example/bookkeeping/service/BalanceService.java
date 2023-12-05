package com.example.bookkeeping.service;

import com.example.bookkeeping.dto.BalanceDto;

public interface BalanceService {
    void changeBalance(BalanceDto balanceDto, Integer balanceId);
    void createBalance(BalanceDto balanceDto);
    BalanceDto getBalanceById(Integer balanceId);
}
