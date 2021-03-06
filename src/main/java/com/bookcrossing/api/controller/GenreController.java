package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.GENRE_MAPPING;

import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.entity.Genre;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.search.SearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GENRE_MAPPING)
public class GenreController extends AbstractSearchController<GenreDTO, Long, BaseNamedSearch> {

    @Autowired
    public GenreController(
            final BaseService<GenreDTO, Long> baseService,
            final SearchService<BaseNamedSearch, List<GenreDTO>> searchService) {
        super(baseService, searchService);
    }
}
