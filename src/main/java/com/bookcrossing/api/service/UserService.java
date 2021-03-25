package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.User;

public interface UserService extends BaseService<UserDTO, User, Long> {

    UserDTO findByLogin(String login);

    boolean existUserByLogin(String login);

    UserDTO registerNewUser(UserDTO user);

    UserDTO getCurrentUser();
}
