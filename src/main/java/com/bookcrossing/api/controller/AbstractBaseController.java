package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.ID_MAPPING;

import com.bookcrossing.api.service.BaseService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractBaseController<DTO, ID> {

    private final BaseService<DTO, ID> baseService;

    protected AbstractBaseController(
            BaseService<DTO, ID> baseService) {
        this.baseService = baseService;
    }

    @PostMapping
    public DTO persist(@RequestBody DTO dto) {
        return baseService.persist(dto);
    }

    @DeleteMapping(ID_MAPPING)
    public void remove(@PathVariable ID id) {
        baseService.remove(id);
    }

    @GetMapping(ID_MAPPING)
    public DTO findById(@PathVariable ID id) {
        return baseService.findById(id);
    }
}
