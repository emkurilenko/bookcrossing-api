package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.entity.Author;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceWrapper extends BaseBaseServiceWrapper<AuthorDTO, Author, Long> {

    @Autowired
    public AuthorServiceWrapper(BaseService<AuthorDTO, Author, Long> service) {
        super(service);
    }
}
