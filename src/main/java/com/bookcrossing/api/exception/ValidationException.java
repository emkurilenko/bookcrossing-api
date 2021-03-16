package com.bookcrossing.api.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private static final String ERROR_CODE = "validation_error";

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ValidationException(
            String resourceName,
            String fieldName,
            Object fieldValue) {
        super(ERROR_CODE);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
