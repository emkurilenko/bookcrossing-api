package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.entity.Author;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorReactiveServiceWrapper extends BaseReactiveBaseServiceWrapper<AuthorDTO, Author, Long> {

    @Autowired
    public AuthorReactiveServiceWrapper(BaseService<AuthorDTO, Author, Long> service) {
        super(service);
    }
}
