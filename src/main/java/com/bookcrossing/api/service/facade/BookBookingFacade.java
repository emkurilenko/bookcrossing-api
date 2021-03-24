package com.bookcrossing.api.service.facade;

import static com.bookcrossing.api.domain.entity.BookStatus.AVAILABLE;
import static com.bookcrossing.api.domain.entity.BookStatus.RESERVED;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKEN_AWAY;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKE_AWAY;

import com.bookcrossing.api.config.ApplicationSetting;
import com.bookcrossing.api.config.dispatcher.Dispatcher;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.BookBookedHistoryDTO;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.dto.book.BookBookingDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.FetchBookDTOReq;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.BookBookingHistoryService;
import com.bookcrossing.api.service.wrapper.BaseBaseServiceWrapper;
import com.bookcrossing.api.validator.Validator;
import com.bookcrossing.api.validator.ValidatorType;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookBookingFacade {

    private final ApplicationSetting applicationSetting;
    private final BookBookingHistoryService bookBookingHistoryService;
    private final BaseBaseServiceWrapper<BookDTO, Book, Long> bookServiceWrapper;
    private final Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher;
    private final BookHistoryService bookHistoryService;

    public BookBookingFacade(
            ApplicationSetting applicationSetting,
            BookBookingHistoryService bookReservationService,
            BaseBaseServiceWrapper<BookDTO, Book, Long> bookServiceWrapper,
            Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher,
            BookHistoryService bookHistoryService) {
        this.applicationSetting = applicationSetting;
        this.bookBookingHistoryService = bookReservationService;
        this.bookServiceWrapper = bookServiceWrapper;
        this.validatorDispatcher = validatorDispatcher;
        this.bookHistoryService = bookHistoryService;
    }

    @Transactional
    public BookBookingDTO bookBook(Long bookId) {
        validatorDispatcher.getByName(ValidatorType.BOOK_BOOKING)
                .validate(bookId);

        BookHistoryDTO availableBook = bookHistoryService.findAvailableBookByBookId(bookId);
        if (availableBook == null) {
            throw new IllegalStateException("book.is.not.available");
        }

        BookDTO bookDTO = availableBook.getBook();
        UserDTO userDTO = UserDTO.builder()
                .id(49L) //todo get from context
                .build();

        ZonedDateTime createdDate = ZonedDateTime.now();
        ZonedDateTime expiredAt = createdDate.plusMinutes(applicationSetting.getLimitReservation());

        BookHistoryDTO history = BookHistoryDTO.builder()
                .user(userDTO)
                .book(bookDTO)
                .status(BookStatus.BOOKED)
                .createdDate(createdDate)
                .build();

        BookBookedHistoryDTO reservationHistoryDTO = BookBookedHistoryDTO
                .builder()
                .createdDate(createdDate)
                .book(bookDTO)
                .user(userDTO)
                .status(BookStatus.BOOKED)
                .expiredAt(expiredAt)
                .history(history)
                .build();

        BookBookingDTO bookReservationDTO = bookBookingHistoryService.reserveBook(
                reservationHistoryDTO);

        availableBook.setStatus(BookStatus.RESERVED);
        bookHistoryService.persist(availableBook);

        return bookReservationDTO;
    }

    @Transactional
    //todo add validator for check available or booked book
    public BookDTO fetchBook(FetchBookDTOReq fetchBookDTO) {
        validatorDispatcher.getByName(ValidatorType.BOOK_CODE)
                .validate(fetchBookDTO);

        //TODO get from context
        UserDTO userDTO = UserDTO.builder()
                .id(fetchBookDTO.getUserId())
                .build();
        Long bookId = fetchBookDTO.getBookId();

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

        BookHistoryDTO persist = bookHistoryService.persist(userHistory);
        bookHistoryService.persist(availableOrReservedHistory);

        return persist.getBook();
    }

}
