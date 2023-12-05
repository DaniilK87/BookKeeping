package com.example.bookkeeping.dto;



import lombok.Data;


@Data
public class BalanceResponseDto {
    private Integer id;
    private Integer balance;
    private String transactionType;
}
