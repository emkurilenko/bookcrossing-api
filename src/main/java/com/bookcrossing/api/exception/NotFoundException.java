package com.bookcrossing.api.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String ERROR_CODE = "not_found_error";


    private final String resourceName;
    private final Object fieldValue;

    public NotFoundException(
            String resourceName,
            Object fieldValue) {
        super(ERROR_CODE);
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public NotFoundException(
            String message,
            String resourceName,
            Object fieldValue) {
        super(message);
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

}
