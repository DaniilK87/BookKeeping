package com.example.bookkeeping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Описание операции по переводу стипендии студенту")
public class StudentGrantDto {

    @JsonProperty("sum")
    @Schema(name = "sum", description = "Сумма стипендии", example = "1000")
    private Integer sum;

    @Schema(name = "date", description = "Дата начисления стипендии", example = "20001-01-01")
    private String date;

    @Schema(name = "type", description = "Тип операции", example = "стипендия")
    private String type;

}
