package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.service.wrapper.ReactiveBaseServiceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController extends AbstractBaseController<AuthorDTO, Long> {

    @Autowired
    public AuthorController(ReactiveBaseServiceWrapper<AuthorDTO, Long> baseServiceWrapper) {
        super(baseServiceWrapper);
    }
}
