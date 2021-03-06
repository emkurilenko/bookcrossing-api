package com.bookcrossing.api.service.wrapper;


public interface BaseServiceWrapper<DTO, ID> {
    DTO persist(DTO value);

    DTO findById(ID id);

    void remove(ID id);
}
