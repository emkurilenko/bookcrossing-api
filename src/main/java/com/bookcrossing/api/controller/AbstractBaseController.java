package com.bookcrossing.api.controller;

import com.bookcrossing.api.service.wrapper.ReactiveBaseServiceWrapper;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractBaseController<DTO, ID> {

    private final ReactiveBaseServiceWrapper<DTO, ID> baseServiceWrapper;

    public AbstractBaseController(ReactiveBaseServiceWrapper<DTO, ID> baseServiceWrapper) {
        this.baseServiceWrapper = baseServiceWrapper;
    }

    @PostMapping
    public Mono<DTO> persist(@RequestBody DTO dto) {
        return baseServiceWrapper.persist(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> remove(@PathVariable ID id) {
        return baseServiceWrapper.remove(id);
    }

    @GetMapping("/{id}")
    public Mono<DTO> findById(@PathVariable ID id) {
        return baseServiceWrapper.findById(id);
    }
}
