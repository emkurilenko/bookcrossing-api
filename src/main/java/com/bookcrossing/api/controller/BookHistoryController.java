package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.BOOKED_REQUEST_MAPPING;
import static com.bookcrossing.api.controller.UrlConstants.BOOK_HISTORY_REQUEST_MAPPING;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.service.BookHistoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BOOK_HISTORY_REQUEST_MAPPING)
public class BookHistoryController {

    private final BookHistoryService bookHistoryService;

    @Autowired
    public BookHistoryController(final BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    @GetMapping
    public List<BookDTO> getUserHistory(BookSearch search) {
        return bookHistoryService.getUserHistory(search);
    }

    @GetMapping(BOOKED_REQUEST_MAPPING)
    public List<BookHistoryDTO> getUserBookedHistory(BookSearch search) {
        return bookHistoryService.getUserBookedHistory(search);
    }
}
