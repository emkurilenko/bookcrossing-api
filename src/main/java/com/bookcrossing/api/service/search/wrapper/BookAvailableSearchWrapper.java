package com.bookcrossing.api.service.search.wrapper;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.service.search.SearchService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class BookAvailableSearchWrapper implements SearchService<BookSearch, List<BookDTO>> {

    private final SearchService<BookSearch, List<BookDTO>> searchService;

    @Autowired
    public BookAvailableSearchWrapper(SearchService<BookSearch, List<BookDTO>> searchService) {
        this.searchService = searchService;
    }

    @Override
    public List<BookDTO> search(BookSearch searchHelper) {
        return searchService.search(searchHelper)
                .stream()
                .peek(item -> item.setStatus(BookStatus.AVAILABLE))
                .collect(Collectors.toList());
    }
}
