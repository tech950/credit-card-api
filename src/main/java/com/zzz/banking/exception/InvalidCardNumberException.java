package com.zzz.banking.exception;

import org.springframework.http.HttpStatus;

public class InvalidCardNumberException extends CCRuntimeException {
    public InvalidCardNumberException(String cardnumber) {
        super(String.format("The Credit Card number %s is invalid.", cardnumber), HttpStatus.BAD_REQUEST);
    }
}
