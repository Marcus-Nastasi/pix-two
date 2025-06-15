package com.open.pix.adapters.exceptions;

import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.domain.exceptions.AccountNumberException;
import com.open.pix.domain.exceptions.AccountTypeException;
import com.open.pix.domain.exceptions.PixKeyException;
import com.open.pix.domain.exceptions.PixTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PixRegistreException.class)
    public ResponseEntity<Object> handlePixRegistreException(PixRegistreException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(AccountNumberException.class)
    public ResponseEntity<Object> handleAccountNumberException(AccountNumberException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(AccountTypeException.class)
    public ResponseEntity<Object> handleAccountTypeException(AccountTypeException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(PixKeyException.class)
    public ResponseEntity<Object> handlePixKeyException(PixKeyException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(PixTypeException.class)
    public ResponseEntity<Object> handlePixTypeException(PixTypeException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }
}
