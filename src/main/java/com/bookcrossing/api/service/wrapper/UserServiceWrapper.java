package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.config.dispatcher.Dispatcher;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.User;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.validator.Validator;
import com.bookcrossing.api.validator.ValidatorType;

import org.springframework.stereotype.Component;

@Component
public class UserServiceWrapper extends BaseBaseServiceWrapper<UserDTO, User, Long> {

    private final Dispatcher<ValidatorType, Validator<UserDTO>> validatorDispatcher;

    public UserServiceWrapper(
            BaseService<UserDTO, User, Long> service,
            Dispatcher<ValidatorType, Validator<UserDTO>> validatorDispatcher) {
        super(service);
        this.validatorDispatcher = validatorDispatcher;
    }

    @Override
    public UserDTO persist(UserDTO value) {
        validatorDispatcher.getByName(ValidatorType.UNIQUE_USER)
                .validate(value);
        return super.persist(value);
    }
}
