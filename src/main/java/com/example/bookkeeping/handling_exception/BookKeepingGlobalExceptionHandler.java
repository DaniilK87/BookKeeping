package com.example.bookkeeping.handling_exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookKeepingGlobalExceptionHandler {
    @ExceptionHandler(value = {NoSuchBalanceException.class})
    public ResponseEntity<ExceptionMessage> noSuchBalanceHandleException(NoSuchBalanceException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {TransferMoneyException.class})
    public ResponseEntity<ExceptionMessage> transferMoneyHandleException(TransferMoneyException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_ACCEPTABLE);
    }

}
