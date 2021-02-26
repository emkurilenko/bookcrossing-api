package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.entity.Genre;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreReactiveServiceWrapper extends BaseReactiveBaseServiceWrapper<GenreDTO, Genre, Long> {
    @Autowired
    public GenreReactiveServiceWrapper(BaseService<GenreDTO, Genre, Long> service) {
        super(service);
    }
}
