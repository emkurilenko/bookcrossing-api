package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.BOOK_ID_BOOKING_MAPPING;
import static com.bookcrossing.api.controller.UrlConstants.BOOK_MAPPING;
import static com.bookcrossing.api.controller.UrlConstants.TAKE_AWAY_BOOK_MAPPING;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.TakeAwayBookReq;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.facade.BookBookingFacade;
import com.bookcrossing.api.service.facade.BookFacade;
import com.bookcrossing.api.service.search.wrapper.BookAvailableSearchWrapper;

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
    private final BookFacade bookFacade;

    @Autowired
    public BookController(
            final BaseService<BookDTO, Long> bookService,
            final BookAvailableSearchWrapper searchService,
            final BookBookingFacade bookBookingFacade,
            final BookFacade bookFacade) {
        super(bookService, searchService);
        this.bookBookingFacade = bookBookingFacade;
        this.bookFacade = bookFacade;
    }

    @Override
    @PostMapping
    public BookDTO persist(@RequestBody BookDTO book) {
        return bookFacade.persist(book);
    }

    @PostMapping(BOOK_ID_BOOKING_MAPPING)
    public BookHistoryDTO booking(@PathVariable Long bookId) {
        return bookBookingFacade.bookBook(bookId);
    }

    @PostMapping(TAKE_AWAY_BOOK_MAPPING)
    public BookHistoryDTO takeAwayBook(@RequestBody TakeAwayBookReq fetchBook) {
        return bookBookingFacade.takeAwayBook(fetchBook);
    }

    @PostMapping("/{bookId}/cancel")
    public BookHistoryDTO cancelBooking(@PathVariable Long bookId) {
        return bookBookingFacade.cancelBooking(bookId);
    }

    @PostMapping("/{bookId}/set-available")
    public BookHistoryDTO setAvailableBook(@PathVariable Long bookId) {
        return bookBookingFacade.setAvailableBook(bookId);
    }
}
