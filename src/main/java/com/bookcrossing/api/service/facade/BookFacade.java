package com.bookcrossing.api.service.facade;

import static com.bookcrossing.api.domain.entity.BookStatus.AVAILABLE;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.UserService;
import com.bookcrossing.api.service.search.SearchService;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookFacade {

    private final SearchService<BookSearch, List<BookDTO>> bookSearchService;
    private final BaseService<BookDTO, Long> bookService;
    private final BookHistoryService bookHistoryService;
    private final UserService userService;

    @Autowired
    public BookFacade(
            final SearchService<BookSearch, List<BookDTO>> bookSearchService,
            final BaseService<BookDTO, Long> bookService,
            final BookHistoryService bookHistoryService,
            final UserService userService) {
        this.bookSearchService = bookSearchService;
        this.bookService = bookService;
        this.bookHistoryService = bookHistoryService;
        this.userService = userService;
    }

    @Transactional
    public BookDTO persist(BookDTO book) {
        final UserDTO currentUser = userService.getCurrentUser();

        BookDTO persistedBook = bookService.persist(book);
        BookHistoryDTO bookHistory = bookHistoryService.findByBookIdAndUserId(
                persistedBook.getId(), currentUser.getId());
        if (bookHistory == null) {
            bookHistory = BookHistoryDTO.builder()
                    .book(persistedBook)
                    .user(currentUser)
                    .status(AVAILABLE)
                    .createdDate(ZonedDateTime.now())
                    .build();
            bookHistoryService.persist(bookHistory);
        }
        return persistedBook;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> findBooksByLocationId(Long locationId) {
        BookSearch bookSearch = BookSearch.builder()
                .locationIds(List.of(locationId))
                .build();
        return bookSearchService.search(bookSearch);
    }
}
