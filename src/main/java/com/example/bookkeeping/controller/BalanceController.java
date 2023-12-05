package com.example.bookkeeping.controller;

import com.example.bookkeeping.dto.BalanceDto;
import com.example.bookkeeping.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Счета", description = "REST API счетов")
public class BalanceController {
    private final BalanceService balanceService;
    @PostMapping("/balance")
    @Operation(summary = "Создать счет", description = "Создается новый счет с зачислением указанной суммы на баланс")
    public ResponseEntity<?> createBalance(@RequestBody BalanceDto balanceDto) {
        balanceService.createBalance(balanceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/balance/change/{balanceId}")
    @Operation(summary = "Пополнить счет", description = " Пополнение баланса указанного счета")
    public ResponseEntity<?> changeBalance(@RequestBody BalanceDto balanceDto, @PathVariable Integer balanceId) {
        balanceService.changeBalance(balanceDto, balanceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/balance/{balanceId}")
    @Operation(summary = "Информация по счету", description = "Получение информации по остатку на счете")
    public ResponseEntity<BalanceDto> balanceById(@PathVariable Integer balanceId) {
        return new ResponseEntity<>(balanceService.getBalanceById(balanceId), HttpStatus.OK);
    }
}
