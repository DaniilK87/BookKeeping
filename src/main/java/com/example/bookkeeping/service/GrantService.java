package com.example.bookkeeping.service;

import com.example.bookkeeping.dto.BalanceResponseDto;
import com.example.bookkeeping.dto.MoneyRequestDto;
import com.example.bookkeeping.dto.RatingResponseDto;

public interface GrantService {

    RatingResponseDto getRating(Integer studentId);

    void transferMoney(MoneyRequestDto moneyRequestDto, Integer balanceId, Integer studentId);

    void changeBalance(BalanceResponseDto balanceResponseDto, Integer balanceId);

    void createBalance(BalanceResponseDto balanceResponseDto);

    BalanceResponseDto getBalanceById(Integer balanceId);
}
