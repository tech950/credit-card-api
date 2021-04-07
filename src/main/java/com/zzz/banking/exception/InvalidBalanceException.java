package com.zzz.banking.exception;

import org.springframework.http.HttpStatus;

public class InvalidBalanceException extends CCRuntimeException {
    public InvalidBalanceException() {
        super("The Credit Card balance should be 0.", HttpStatus.BAD_REQUEST);
    }
}
