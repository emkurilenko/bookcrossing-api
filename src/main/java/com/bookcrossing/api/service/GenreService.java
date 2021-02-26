package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.entity.Genre;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends DefaultBaseService<GenreDTO, Genre, Long> {

    @Autowired
    public GenreService(
            final BaseMapper<GenreDTO, Genre> mapper,
            final BaseCrudRepository<Genre, Long> repository) {
        super(mapper, repository);
    }
}
