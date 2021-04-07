package com.zzz.banking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomisedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CCRuntimeException.class)
    public final ResponseEntity<Object> handleAllException(CCRuntimeException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), ex.getStatus());
        return new ResponseEntity<>(response, ex.getStatus());
    }

}
