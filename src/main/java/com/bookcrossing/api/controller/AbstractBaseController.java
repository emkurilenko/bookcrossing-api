package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.ID_MAPPING;
import static com.bookcrossing.api.controller.UrlConstants.SEARCH_MAPPING;

import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.ReactiveBaseServiceWrapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractBaseController<DTO, ID, SEARCH> {

    private final ReactiveBaseServiceWrapper<DTO, ID> baseServiceWrapper;
    private final SearchService<SEARCH, List<DTO>> searchService;

    protected AbstractBaseController(
            ReactiveBaseServiceWrapper<DTO, ID> baseServiceWrapper,
            SearchService<SEARCH, List<DTO>> searchService) {
        this.baseServiceWrapper = baseServiceWrapper;
        this.searchService = searchService;
    }

    @PostMapping
    public Mono<DTO> persist(@RequestBody DTO dto) {
        return baseServiceWrapper.persist(dto);
    }

    @DeleteMapping(ID_MAPPING)
    public Mono<Void> remove(@PathVariable ID id) {
        return baseServiceWrapper.remove(id);
    }

    @GetMapping(ID_MAPPING)
    public Mono<DTO> findById(@PathVariable ID id) {
        return baseServiceWrapper.findById(id);
    }

    @GetMapping(SEARCH_MAPPING)
    public Flux<DTO> search(SEARCH search) {
        return Mono.fromCallable(() -> searchService.search(search))
                .flatMapMany(Flux::fromIterable);
    }
}
