package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractBaseController<UserDTO, Long> {

    @Autowired
    public UserController(BaseServiceWrapper<UserDTO, Long> baseServiceWrapper) {
        super(baseServiceWrapper);
    }
}
