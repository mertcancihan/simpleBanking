package com.eteration.simplebanking.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InsufficientBalanceException extends Exception {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleInsufficientBalanceException(RuntimeException ex) {
        if ("Yetersiz bakiye!".equals(ex.getMessage())) {
            return new ResponseEntity<>("Yetersiz bakiye!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Beklenmeyen bir hata oluştu.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
