package com.bookcrossing.api.service.facade;

import static com.bookcrossing.api.domain.entity.BookStatus.AVAILABLE;
import static com.bookcrossing.api.domain.entity.BookStatus.BOOKED;
import static com.bookcrossing.api.domain.entity.BookStatus.CANCELED;
import static com.bookcrossing.api.domain.entity.BookStatus.RESERVED;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKEN_AWAY;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKE_AWAY;
import static java.util.Optional.ofNullable;

import com.bookcrossing.api.config.dispatcher.Dispatcher;
import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.BookRatingDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.TakeAwayBookReq;
import com.bookcrossing.api.domain.dto.user.UserDTO;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.exception.NotFoundException;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.BookRatingService;
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
    private final BookRatingService bookRatingService;

    @Autowired
    public BookBookingFacade(
            Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher,
            BookHistoryService bookHistoryService,
            UserService userService,
            BookRatingService bookRatingService) {
        this.validatorDispatcher = validatorDispatcher;
        this.bookHistoryService = bookHistoryService;
        this.userService = userService;
        this.bookRatingService = bookRatingService;
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

        BookHistoryDTO previousHistory = bookHistoryService.findByBookIdAndUserId(
                bookId,
                userDTO.getId());

        ZonedDateTime createdDate = ZonedDateTime.now();

        BookHistoryDTO history = BookHistoryDTO.builder()
                .user(userDTO)
                .book(bookDTO)
                .status(BOOKED)
                .createdDate(createdDate)
                .build();

        ofNullable(previousHistory)
                .map(BaseEntityDTO::getId)
                .ifPresent(history::setId);

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
        if (availableOrReservedHistory == null) {
            throw new IllegalStateException("book.is.not.available"); //TODO message and add validation
        }

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

        BookRatingDTO bookRatingDTO = BookRatingDTO.builder()
                .bookId(bookId)
                .createdDate(ZonedDateTime.now())
                .rate(takeAwayBookReq.getRate())
                .ownerId(availableOrReservedHistory.getUser().getId())
                .userId(userDTO.getId())
                .build();

        bookRatingService.persist(bookRatingDTO);

        return bookHistoryService.persist(userHistory);
    }

    @Transactional
    public BookHistoryDTO cancelBooking(Long bookId) {
        UserDTO currentUser = userService.getCurrentUser();
        BookHistoryDTO bookedBook = bookHistoryService.findByBookIdAndUserId(
                bookId,
                currentUser.getId());

        BookHistoryDTO reservedBook = bookHistoryService.findByBookIdAndStatuses(
                bookId,
                List.of(RESERVED));

        if (reservedBook == null) {
            throw new NotFoundException("not.found.reserved.book.in.history", "book", bookId);
        }

        reservedBook.setStatus(AVAILABLE);
        bookHistoryService.persist(reservedBook);

        bookedBook.setStatus(CANCELED);
        return bookHistoryService.persist(bookedBook);
    }

    @Transactional
    public BookHistoryDTO setAvailableBook(Long bookId) {
        //TODO add validator

        UserDTO currentUser = userService.getCurrentUser();
        BookHistoryDTO bookHistoryUser = bookHistoryService.findByBookIdAndUserId(
                bookId,
                currentUser.getId());
        if (bookHistoryUser.getStatus() != TAKE_AWAY) {
            throw new IllegalStateException(String.format(
                    "the.book.is.not.in.status: %s",
                    TAKE_AWAY)); //todo move to validator
        }

        bookHistoryUser.setStatus(AVAILABLE);
        return bookHistoryService.persist(bookHistoryUser);
    }
}
