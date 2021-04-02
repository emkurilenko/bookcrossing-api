package com.bookcrossing.api.validator;

import static com.bookcrossing.api.validator.ValidatorType.UNIQUE_USER;

import com.bookcrossing.api.domain.dto.user.UserDTO;
import com.bookcrossing.api.domain.repository.UserRepository;
import com.bookcrossing.api.exception.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUserValidator implements Validator<UserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public UniqueUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserDTO value) {
        boolean existsByLogin = userRepository.existsByLogin(value.getLogin());
        if (existsByLogin) {
            throw new ValidationException(
                    "user",
                    "login",
                    value.getLogin());
        }
        boolean existsByEmail = userRepository.existsByEmail(value.getEmail());
        if (existsByEmail) {
            throw new ValidationException(
                    "user",
                    "email",
                    value.getEmail());
        }
    }

    @Override
    public ValidatorType type() {
        return UNIQUE_USER;
    }
}
