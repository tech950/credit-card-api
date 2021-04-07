package com.zzz.banking.exception;

import org.springframework.http.HttpStatus;

public class CCRuntimeException extends RuntimeException {

    public HttpStatus getStatus() {
        return status;
    }

    private HttpStatus status;

    public CCRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
