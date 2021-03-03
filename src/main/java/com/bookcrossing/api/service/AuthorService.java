package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.entity.Author;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends DefaultBaseService<AuthorDTO, Author, Long> {

    @Autowired
    public AuthorService(
            final BaseMapper<AuthorDTO, Author> mapper,
            final BaseCrudRepository<Author, Long> repository) {
        super(mapper, repository);
    }
}
