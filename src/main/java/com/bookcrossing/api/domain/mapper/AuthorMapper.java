package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.entity.Author;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = RETURN_DEFAULT,
        unmappedTargetPolicy = IGNORE)
public interface AuthorMapper extends BaseMapper<AuthorDTO, Author> {
}
