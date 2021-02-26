package com.bookcrossing.api.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig
public interface BaseMapper<DTO, ENTITY> {
    DTO mapToDTO(ENTITY entity);

    @Mapping(target = "isDeleted", ignore = true)
    ENTITY mapToEntity(DTO dto);
}
