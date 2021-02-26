package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.service.BaseService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BaseReactiveBaseServiceWrapper<DTO, ENTITY, ID> implements ReactiveBaseServiceWrapper<DTO, ID> {

    private final BaseService<DTO, ENTITY, ID> service;

    @Override
    public Mono<DTO> persist(DTO value) {
        return Mono.fromCallable(() -> service.persist(value));
    }

    @Override
    public Mono<DTO> findById(ID id) {
        return Mono.fromCallable(() -> service.findById(id));
    }

    @Override
    public Mono<Void> remove(ID id) {
        return Mono.just(id)
                .doOnNext(service::remove)
                .then();
    }
}
