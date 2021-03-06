package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.service.BaseService;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BaseBaseServiceWrapper<DTO, ENTITY, ID> implements BaseServiceWrapper<DTO, ID> {

    private final BaseService<DTO, ENTITY, ID> service;

    @Override
    @Transactional
    public DTO persist(DTO value) {
        return service.persist(value);
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        return service.findById(id);
    }

    @Override
    public void remove(ID id) {
        service.remove(id);
    }
}
