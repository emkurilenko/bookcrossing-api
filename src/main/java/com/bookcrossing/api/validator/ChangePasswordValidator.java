package com.bookcrossing.api.validator;

import static com.bookcrossing.api.validator.ValidatorType.CHANGE_PASSWORD;

import com.bookcrossing.api.domain.dto.user.ChangePasswordRequest;
import com.bookcrossing.api.domain.dto.user.UserDTO;
import com.bookcrossing.api.domain.entity.User;
import com.bookcrossing.api.domain.repository.UserRepository;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordValidator implements Validator<ChangePasswordRequest> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ChangePasswordValidator(
            final UserRepository userRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void validate(ChangePasswordRequest value) {
        User user = userRepository.findById(value.getUserId())
                .orElseThrow(() -> new IllegalStateException("user.not.found"));//todo change message

        if (!passwordEncoder.matches(value.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("old.password.does.not.match");
        }

    }

    @Override
    public ValidatorType type() {
        return CHANGE_PASSWORD;
    }
}
