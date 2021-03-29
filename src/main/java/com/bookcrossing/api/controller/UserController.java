package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.UserDTO;
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

    @PostMapping("/register")
    public UserDTO registerNewUser(@RequestBody UserDTO user) {
        return userService.registerNewUser(user);
    }

    @GetMapping
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }
}
