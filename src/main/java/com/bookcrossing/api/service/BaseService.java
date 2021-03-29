package com.bookcrossing.api.service;

public interface BaseService<DTO, ID> {
    DTO persist(DTO value);

    DTO findById(ID id);

    void remove(ID id);
}
