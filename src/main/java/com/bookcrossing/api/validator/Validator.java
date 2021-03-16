package com.bookcrossing.api.validator;

public interface Validator<T> {
    void validate(T value);

    ValidatorType type();
}
