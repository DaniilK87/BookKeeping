package com.example.bookkeeping.dto;

import com.example.bookkeeping.entity.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Описание счета")
public class BalanceDto {

    @Schema(name = "id", description = "Идентификатор счета", example = "1")
    private Integer id;

    @Schema(name = "sum", description = "Сумма денег на счете", example = "1000")
    private Integer balance;

    @Schema(name = "type", description = "Тип счета", example = "зарплата")
    private TransactionType type;
}
