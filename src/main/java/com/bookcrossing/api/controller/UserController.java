package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.CHANGE_PASSWORD_REQUEST_MAPPING;

import com.bookcrossing.api.domain.dto.user.ChangePasswordRequest;
import com.bookcrossing.api.domain.dto.user.UserDTO;
import com.bookcrossing.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDTO login() {
        return userService.getCurrentUser();
    }

    @PostMapping("/register")
    public UserDTO registerNewUser(@RequestBody UserDTO user) {
        return userService.registerNewUser(user);
    }

    @PostMapping
    public UserDTO update(@RequestBody UserDTO user) {
        return userService.persist(user);
    }

    @GetMapping
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PostMapping(CHANGE_PASSWORD_REQUEST_MAPPING)
    public UserDTO changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        UserDTO currentUser = userService.getCurrentUser();
        changePasswordRequest.setUserId(currentUser.getId());
        return userService.changePassword(changePasswordRequest);
    }
}
