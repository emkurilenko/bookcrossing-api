package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.entity.Author;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends BaseMapper<AuthorDTO, Author> {
}
