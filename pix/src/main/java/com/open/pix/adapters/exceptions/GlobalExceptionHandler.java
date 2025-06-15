package com.open.pix.adapters.exceptions;

import com.open.pix.application.exceptions.NotFoundException;
import com.open.pix.application.exceptions.PixRegistreException;
import com.open.pix.application.exceptions.PixUpdateException;
import com.open.pix.domain.exceptions.*;
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(PixRegistreException.class)
    public ResponseEntity<Object> handlePixRegistreException(PixRegistreException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(PixUpdateException.class)
    public ResponseEntity<Object> handlePixUpdateException(PixUpdateException exception, WebRequest request) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(AgencyNumberException.class)
    public ResponseEntity<Object> handleAgencyNumberException(AgencyNumberException exception, WebRequest request) {
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
