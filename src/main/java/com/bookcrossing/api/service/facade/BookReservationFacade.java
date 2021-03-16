package com.bookcrossing.api.service.facade;

import com.bookcrossing.api.config.ApplicationSetting;
import com.bookcrossing.api.config.dispatcher.Dispatcher;
import com.bookcrossing.api.domain.dto.BookReservationHistoryDTO;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.book.BookReservationDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.service.BookReservationService;
import com.bookcrossing.api.service.wrapper.BaseBaseServiceWrapper;
import com.bookcrossing.api.validator.Validator;
import com.bookcrossing.api.validator.ValidatorType;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

@Component
public class BookReservationFacade {

    private final ApplicationSetting applicationSetting;
    private final BookReservationService bookReservationService;
    private final BaseBaseServiceWrapper<BookDTO, Book, Long> bookServiceWrapper;
    private final Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher;

    public BookReservationFacade(
            ApplicationSetting applicationSetting,
            BookReservationService bookReservationService,
            BaseBaseServiceWrapper<BookDTO, Book, Long> bookServiceWrapper,
            Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher) {
        this.applicationSetting = applicationSetting;
        this.bookReservationService = bookReservationService;
        this.bookServiceWrapper = bookServiceWrapper;
        this.validatorDispatcher = validatorDispatcher;
    }

    public BookReservationDTO reserveBook(Long bookId) {
        validatorDispatcher.getByName(ValidatorType.BOOK_RESERVATION)
                .validate(bookId);
        BookDTO bookDTO = bookServiceWrapper.findById(bookId);
        UserDTO userDTO = UserDTO.builder()
                .id(49L) //todo got from context
                .build();

        ZonedDateTime createdDate = ZonedDateTime.now();
        ZonedDateTime expiredAt = createdDate.plusMinutes(applicationSetting.getLimitReservation());

        BookReservationHistoryDTO reservationHistoryDTO = BookReservationHistoryDTO
                .builder()
                .createdDate(createdDate)
                .book(bookDTO)
                .user(userDTO)
                .status(BookStatus.RESERVATION)
                .expiredAt(expiredAt)
                .build();

        return bookReservationService.reserveBook(reservationHistoryDTO);
    }

}
