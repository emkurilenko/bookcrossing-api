package com.bookcrossing.api.controller.advice;

import com.bookcrossing.api.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//TODO mapping
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<?> handleValidationException(ValidationException e) {
//        log.error(e.getMessage());
//        return formResponse(HttpStatus.BAD_REQUEST, e.get);
//    }

    private ResponseEntity formResponse(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(body);
    }
}
