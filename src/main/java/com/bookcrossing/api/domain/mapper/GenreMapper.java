package com.bookcrossing.api.domain.mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.entity.Genre;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = RETURN_DEFAULT,
        unmappedTargetPolicy = IGNORE)
public interface GenreMapper extends BaseMapper<GenreDTO, Genre> {

}
