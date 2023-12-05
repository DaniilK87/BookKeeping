package com.example.bookkeeping.handling_exception;

public class TransferMoneyException extends Exception {
    public TransferMoneyException(String message) {
        super(message);
    }
}
