package com.bookcrossing.api.validator;

import static com.bookcrossing.api.validator.ValidatorType.BOOK_BOOKING;

import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.repository.BookBookingHistoryRepository;
import com.bookcrossing.api.exception.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookReservationValidator implements Validator<Long> {

    private final BookBookingHistoryRepository bookReservationHistoryRepository;

    @Autowired
    public BookReservationValidator(BookBookingHistoryRepository bookReservationHistoryRepository) {
        this.bookReservationHistoryRepository = bookReservationHistoryRepository;
    }

    @Override
    public void validate(Long value) {
        boolean isBookReserve = bookReservationHistoryRepository.existsByBookIdAndStatus(
                value,
                BookStatus.BOOKED);

        if (isBookReserve) {
            throw new ValidationException("book.reserve", "book", "book_id", value);
        }
    }

    @Override
    public ValidatorType type() {
        return BOOK_BOOKING;
    }
}
