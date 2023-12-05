package com.example.bookkeeping.handling_exception;


import jakarta.persistence.EntityNotFoundException;

public class NoSuchBalanceException extends RuntimeException {
    public NoSuchBalanceException(String message) {
        super(message);
    }
}
