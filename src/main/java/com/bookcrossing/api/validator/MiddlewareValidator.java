package com.bookcrossing.api.validator;

import com.bookcrossing.api.config.dispatcher.Dispatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiddlewareValidator {

    private final Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher;

    @Autowired
    public MiddlewareValidator(Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher) {
        this.validatorDispatcher = validatorDispatcher;
    }

    public void validate(Object value, List<ValidatorType> validators) {
        validators
                .stream()
                .map(validatorDispatcher::getByName)
                .forEach(service -> service.validate(value));
    }

}
