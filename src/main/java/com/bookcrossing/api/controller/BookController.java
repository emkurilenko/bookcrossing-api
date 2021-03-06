package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.BOOK_MAPPING;

import com.bookcrossing.api.domain.dto.BookDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BOOK_MAPPING)
public class BookController extends AbstractBaseController<BookDTO, Long, BookSearch> {

    @Autowired
    public BookController(
            final BaseServiceWrapper<BookDTO, Long> baseServiceWrapper,
            final SearchService<BookSearch, List<BookDTO>> searchService) {
        super(baseServiceWrapper, searchService);
    }
}
