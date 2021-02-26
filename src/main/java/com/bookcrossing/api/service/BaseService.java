package com.bookcrossing.api.service;

public interface BaseService<DTO, ENTITY, ID> {
    DTO persist(DTO value);

    DTO findById(ID id);

    void remove(ID id);

    ENTITY mapToEntity(DTO value);

    DTO mapToDto(ENTITY value);
}
