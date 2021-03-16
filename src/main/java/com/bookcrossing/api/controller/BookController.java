package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.BOOK_ID_RESERV_MAPPING;
import static com.bookcrossing.api.controller.UrlConstants.BOOK_MAPPING;

import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.BookReservationDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.service.facade.BookReservationFacade;
import com.bookcrossing.api.service.search.SearchService;
import com.bookcrossing.api.service.wrapper.BaseServiceWrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BOOK_MAPPING)
public class BookController extends AbstractSearchController<BookDTO, Long, BookSearch> {

    private final BookReservationFacade bookReservationFacade;

    @Autowired
    public BookController(
            final BaseServiceWrapper<BookDTO, Long> baseServiceWrapper,
            final SearchService<BookSearch, List<BookDTO>> searchService,
            final BookReservationFacade bookReservationFacade) {
        super(baseServiceWrapper, searchService);
        this.bookReservationFacade = bookReservationFacade;
    }

    @PostMapping(BOOK_ID_RESERV_MAPPING)
    public BookReservationDTO reserve(@PathVariable Long bookId) {
        return bookReservationFacade.reserveBook(bookId);
    }

}
