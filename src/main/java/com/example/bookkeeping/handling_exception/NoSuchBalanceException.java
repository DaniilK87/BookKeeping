package com.example.bookkeeping.handling_exception;


import javax.persistence.EntityNotFoundException;

public class NoSuchBalanceException extends EntityNotFoundException {

    public NoSuchBalanceException(String message) {
        super(message);
    }
}
