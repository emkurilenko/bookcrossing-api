package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.SEARCH_MAPPING;

import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class AbstractSearchController<DTO, ID, SEARCH> extends AbstractBaseController<DTO, ID> {

    private final SearchService<SEARCH, List<DTO>> searchService;

    protected AbstractSearchController(
            BaseServiceWrapper<DTO, ID> baseServiceWrapper,
            SearchService<SEARCH, List<DTO>> searchService) {
        super(baseServiceWrapper);
        this.searchService = searchService;
    }

    @GetMapping(SEARCH_MAPPING)
    public List<DTO> search(SEARCH search) {
        return searchService.search(search);
    }

}
