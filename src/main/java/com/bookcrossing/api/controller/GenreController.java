package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.service.wrapper.ReactiveBaseServiceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController extends AbstractBaseController<GenreDTO, Long> {

    @Autowired
    public GenreController(ReactiveBaseServiceWrapper<GenreDTO, Long> baseServiceWrapper) {
        super(baseServiceWrapper);
    }
}
