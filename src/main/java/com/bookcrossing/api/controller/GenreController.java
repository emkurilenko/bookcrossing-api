package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.GENRE_MAPPING;

import com.bookcrossing.api.domain.dto.GenreDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.ReactiveBaseServiceWrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GENRE_MAPPING)
public class GenreController extends AbstractBaseController<GenreDTO, Long, BaseNamedSearch> {

    @Autowired
    public GenreController(
            final ReactiveBaseServiceWrapper<GenreDTO, Long> baseServiceWrapper,
            final SearchService<BaseNamedSearch, List<GenreDTO>> searchService) {
        super(baseServiceWrapper, searchService);
    }
}
