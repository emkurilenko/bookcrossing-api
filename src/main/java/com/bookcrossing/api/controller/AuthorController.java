package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.AUTHOR_MAPPING;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.search.SearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AUTHOR_MAPPING)
public class AuthorController extends AbstractSearchController<AuthorDTO, Long, BaseNamedSearch> {

    @Autowired
    public AuthorController(
            final BaseService<AuthorDTO, Long> baseService,
            final SearchService<BaseNamedSearch, List<AuthorDTO>> searchService) {
        super(baseService, searchService);
    }
}
