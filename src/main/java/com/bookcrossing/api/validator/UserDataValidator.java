package com.bookcrossing.api.validator;

import static com.bookcrossing.api.validator.ValidatorType.USER_DATA;

import com.bookcrossing.api.domain.dto.user.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class UserDataValidator implements Validator<UserDTO> {

    @Override
    public void validate(UserDTO value) {

    }

    @Override
    public ValidatorType type() {
        return USER_DATA;
    }
}
