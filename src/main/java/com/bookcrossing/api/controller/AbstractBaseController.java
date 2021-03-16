package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.ID_MAPPING;

import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractBaseController<DTO, ID> {

    private final BaseServiceWrapper<DTO, ID> baseServiceWrapper;

    protected AbstractBaseController(
            BaseServiceWrapper<DTO, ID> baseServiceWrapper) {
        this.baseServiceWrapper = baseServiceWrapper;
    }

    @PostMapping
    public DTO persist(@RequestBody DTO dto) {
        return baseServiceWrapper.persist(dto);
    }

    @DeleteMapping(ID_MAPPING)
    public void remove(@PathVariable ID id) {
        baseServiceWrapper.remove(id);
    }

    @GetMapping(ID_MAPPING)
    public DTO findById(@PathVariable ID id) {
        return baseServiceWrapper.findById(id);
    }
}
