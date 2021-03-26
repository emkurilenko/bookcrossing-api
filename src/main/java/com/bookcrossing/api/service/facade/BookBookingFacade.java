package com.bookcrossing.api.service.facade;

import static com.bookcrossing.api.domain.entity.BookStatus.AVAILABLE;
import static com.bookcrossing.api.domain.entity.BookStatus.RESERVED;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKEN_AWAY;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKE_AWAY;

import com.bookcrossing.api.config.dispatcher.Dispatcher;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.TakeAwayBookReq;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.UserService;
import com.bookcrossing.api.validator.Validator;
import com.bookcrossing.api.validator.ValidatorType;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookBookingFacade {

    private final Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher;
    private final BookHistoryService bookHistoryService;
    private final UserService userService;

    @Autowired
    public BookBookingFacade(
            Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher,
            BookHistoryService bookHistoryService,
            UserService userService) {
        this.validatorDispatcher = validatorDispatcher;
        this.bookHistoryService = bookHistoryService;
        this.userService = userService;
    }

    @Transactional
    public BookHistoryDTO bookBook(Long bookId) {
        validatorDispatcher.getByName(ValidatorType.BOOK_BOOKING)
                .validate(bookId);

        BookHistoryDTO availableBook = bookHistoryService.findAvailableBookByBookId(bookId);
        if (availableBook == null) {
            throw new IllegalStateException("book.is.not.available");
        }

        BookDTO bookDTO = availableBook.getBook();
        UserDTO userDTO = userService.getCurrentUser();

        ZonedDateTime createdDate = ZonedDateTime.now();

        BookHistoryDTO history = BookHistoryDTO.builder()
                .user(userDTO)
                .book(bookDTO)
                .status(BookStatus.BOOKED)
                .createdDate(createdDate)
                .build();

        availableBook.setStatus(BookStatus.RESERVED);
        bookHistoryService.persist(availableBook);

        return bookHistoryService.persist(history);
    }

    @Transactional
    //todo add validator for check available or booked book
    public BookHistoryDTO takeAwayBook(TakeAwayBookReq takeAwayBookReq) {
        validatorDispatcher.getByName(ValidatorType.BOOK_CODE)
                .validate(takeAwayBookReq);

        UserDTO userDTO = userService.getCurrentUser();
        Long bookId = takeAwayBookReq.getBookId();

        BookHistoryDTO availableOrReservedHistory = bookHistoryService.findByBookIdAndStatuses(
                bookId, List.of(AVAILABLE, RESERVED));

        availableOrReservedHistory.setStatus(TAKEN_AWAY);

        BookHistoryDTO userHistory = bookHistoryService.findByBookIdAndUserId(
                bookId,
                userDTO.getId());
        if (userHistory == null) {
            BookDTO book = availableOrReservedHistory.getBook();
            userHistory = BookHistoryDTO.builder()
                    .createdDate(ZonedDateTime.now())
                    .user(userDTO)
                    .book(book)
                    .status(TAKE_AWAY)
                    .build();
        } else {
            userHistory.setStatus(TAKE_AWAY);
        }
        bookHistoryService.persist(availableOrReservedHistory);

        return bookHistoryService.persist(userHistory);
    }

}
