package com.example.bookkeeping.handling_exception;

public class TransferMoneyException extends RuntimeException {
    public TransferMoneyException(String message) {
        super(message);
    }
}
