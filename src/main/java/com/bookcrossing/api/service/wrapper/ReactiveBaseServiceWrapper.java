package com.bookcrossing.api.service.wrapper;

import reactor.core.publisher.Mono;

public interface ReactiveBaseServiceWrapper<DTO, ID> {
    Mono<DTO> persist(DTO value);

    Mono<DTO> findById(ID id);

    Mono<Void> remove(ID id);
}
