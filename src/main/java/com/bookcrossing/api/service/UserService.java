package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.user.ChangePasswordRequest;
import com.bookcrossing.api.domain.dto.user.UserDTO;

public interface UserService extends BaseService<UserDTO, Long> {

    UserDTO findByLogin(String login);

    boolean existUserByLogin(String login);

    UserDTO registerNewUser(UserDTO user);

    UserDTO getCurrentUser();

    UserDTO changePassword(ChangePasswordRequest changePasswordRequest);
}
