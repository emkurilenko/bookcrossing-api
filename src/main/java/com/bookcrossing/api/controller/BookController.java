package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.BOOK_ID_BOOKING_MAPPING;
import static com.bookcrossing.api.controller.UrlConstants.BOOK_MAPPING;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.BookBookingDTO;
import com.bookcrossing.api.domain.dto.book.FetchBookDTOReq;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.service.facade.BookBookingFacade;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BOOK_MAPPING)
public class BookController extends AbstractSearchController<BookDTO, Long, BookSearch> {

    private final BookBookingFacade bookBookingFacade;

    @Autowired
    public BookController(
            final BaseServiceWrapper<BookDTO, Long> baseServiceWrapper,
            final SearchService<BookSearch, List<BookDTO>> searchService,
            final BookBookingFacade bookBookingFacade) {
        super(baseServiceWrapper, searchService);
        this.bookBookingFacade = bookBookingFacade;
    }

    @PostMapping(BOOK_ID_BOOKING_MAPPING)
    public BookBookingDTO booking(@PathVariable Long bookId) {
        return bookBookingFacade.bookBook(bookId);
    }

    @PostMapping("/fetch")
    public BookDTO fetchBook(@RequestBody FetchBookDTOReq fetchBook) {
        return bookBookingFacade.fetchBook(fetchBook);
    }
}
