package com.bookcrossing.api.validator;

import com.bookcrossing.api.domain.dto.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class LoginUserValidator implements Validator<UserDTO> {

    @Override
    public void validate(UserDTO value) {

    }

    @Override
    public ValidatorType type() {
        return ValidatorType.LOGIN_USER;
    }
}
