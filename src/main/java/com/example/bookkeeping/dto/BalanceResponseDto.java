package com.example.bookkeeping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponseDto {
    private Integer id;
    private Integer balance;
    private String transactionType;
}
