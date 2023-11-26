package com.example.bookkeeping.controller;

import com.example.bookkeeping.dto.BalanceResponseDto;
import com.example.bookkeeping.dto.MoneyRequestDto;
import com.example.bookkeeping.dto.RatingResponseDto;
import com.example.bookkeeping.service.GrantService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Data
@RequiredArgsConstructor
public class GrantController {

    private final GrantService grantService;

    @PostMapping("/createBalance")
    public void createBalance(@RequestBody BalanceResponseDto balanceResponseDto) {
        grantService.createBalance(balanceResponseDto);
    }

    @PutMapping("/changeBalance/{balanceId}")
    public void upBalance(@RequestBody BalanceResponseDto balanceResponseDto, @PathVariable Integer balanceId) {
        grantService.changeBalance(balanceResponseDto, balanceId);
    }

    @GetMapping("/rating/{studentId}")
    public RatingResponseDto getRating(@PathVariable Integer studentId) {
        return grantService.getRating(studentId);
    }

    @PostMapping("/transaction/{balanceId}/{studentId}")
    public void transferMoney(@RequestBody MoneyRequestDto moneyRequestDto,
                              @PathVariable Integer balanceId,@PathVariable Integer studentId) {
        grantService.transferMoney(moneyRequestDto,balanceId,studentId);
    }

    @GetMapping("/balance/{balanceId}")
    public BalanceResponseDto getBalanceById(@PathVariable Integer balanceId) {
        return grantService.getBalanceById(balanceId);
    }
}
