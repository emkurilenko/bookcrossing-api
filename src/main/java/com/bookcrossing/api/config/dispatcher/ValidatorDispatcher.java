package com.bookcrossing.api.config.dispatcher;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toMap;

import com.bookcrossing.api.validator.Validator;
import com.bookcrossing.api.validator.ValidatorType;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorDispatcher<T> implements Dispatcher<ValidatorType, Validator<T>> {

    private final Map<ValidatorType, Validator<T>> validatorMap;

    @Autowired
    public ValidatorDispatcher(List<Validator<T>> validators) {
        this.validatorMap = validators.stream().collect(toMap(Validator::type, v -> v));
    }

    @Override
    public Validator<T> getByName(ValidatorType name) {
        return checkNotNull(validatorMap.get(name), "Cannot find Validator for name = " + name);
    }

}
