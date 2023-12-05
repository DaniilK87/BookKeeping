package com.example.bookkeeping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Описание операции по переводу зарплаты преподавателю")
public class TeacherSalaryDto {

    @JsonProperty("sum")
    @Schema(name = "sum", description = "Сумма зарплаты", example = "1000")
    private Integer sum;

    @Schema(name = "date", description = "Дата начисления зарплаты", example = "1000")
    private String date;

    @Schema(name = "type", description = "Тип операции", example = "зарплата")
    private String type;
}
