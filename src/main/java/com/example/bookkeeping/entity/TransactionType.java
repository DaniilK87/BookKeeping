package com.example.bookkeeping.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TransactionType {
    SALARY("зарплата"), GRANT("стипендия");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }
}
