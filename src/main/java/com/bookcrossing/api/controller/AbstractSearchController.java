package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.SEARCH_MAPPING;

import com.bookcrossing.api.domain.dto.search.SearchHelper;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.search.SearchService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class AbstractSearchController<DTO, ID, SEARCH extends SearchHelper> extends
        AbstractBaseController<DTO, ID> {

    private final SearchService<SEARCH, List<DTO>> searchService;

    protected AbstractSearchController(
            BaseService<DTO, ID> baseService,
            SearchService<SEARCH, List<DTO>> searchService) {
        super(baseService);
        this.searchService = searchService;
    }

    @GetMapping(SEARCH_MAPPING)
    public List<DTO> search(SEARCH search) {
        return searchService.search(search);
    }

}
