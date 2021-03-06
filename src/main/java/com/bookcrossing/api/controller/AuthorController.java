package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.AUTHOR_MAPPING;

import com.bookcrossing.api.domain.dto.AuthorDTO;
import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AUTHOR_MAPPING)
public class AuthorController extends AbstractBaseController<AuthorDTO, Long, BaseNamedSearch> {

    @Autowired
    public AuthorController(
            final BaseServiceWrapper<AuthorDTO, Long> baseServiceWrapper,
            final SearchService<BaseNamedSearch, List<AuthorDTO>> searchService) {
        super(baseServiceWrapper, searchService);
    }
}
