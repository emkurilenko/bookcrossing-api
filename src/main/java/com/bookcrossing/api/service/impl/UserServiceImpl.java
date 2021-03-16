package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.User;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;
import com.bookcrossing.api.domain.repository.UserRepository;
import com.bookcrossing.api.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends DefaultBaseService<UserDTO, User, Long> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(
            BaseMapper<UserDTO, User> mapper,
            UserRepository userRepository) {
        super(mapper, userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public boolean existUserByLogin(String login) {
        return false;
    }
}
