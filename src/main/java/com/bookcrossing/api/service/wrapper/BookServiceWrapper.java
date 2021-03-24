package com.bookcrossing.api.service.wrapper;

import static com.bookcrossing.api.domain.entity.BookStatus.AVAILABLE;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.service.BookHistoryService;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookServiceWrapper extends BaseBaseServiceWrapper<BookDTO, Book, Long> {

    private final BookHistoryService bookHistoryService;

    @Autowired
    public BookServiceWrapper(
            final BaseService<BookDTO, Book, Long> service,
            final BookHistoryService bookHistoryService) {
        super(service);
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    @Transactional
    public BookDTO persist(BookDTO value) {
        final Long userId = 6L; //TODO
        BookDTO persistedBook = super.persist(value);
        BookHistoryDTO bookHistory = bookHistoryService.findByBookIdAndUserId(
                persistedBook.getId(), userId);
        if (bookHistory == null) {
            bookHistory = BookHistoryDTO.builder()
                    .book(persistedBook)
                    .user(UserDTO.builder()
                            .id(userId)
                            .build())
                    .status(AVAILABLE)
                    .createdDate(ZonedDateTime.now())
                    .build();
            bookHistoryService.persist(bookHistory);
        }
        return persistedBook;
    }
}
