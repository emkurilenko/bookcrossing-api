package com.bookcrossing.api.validator;

import static com.bookcrossing.api.validator.ValidatorType.BOOK_RESERVATION;

import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.repository.BookReservationHistoryRepository;
import com.bookcrossing.api.exception.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookReservationValidator implements Validator<Long> {

    private final BookReservationHistoryRepository bookReservationHistoryRepository;

    @Autowired
    public BookReservationValidator(BookReservationHistoryRepository bookReservationHistoryRepository) {
        this.bookReservationHistoryRepository = bookReservationHistoryRepository;
    }

    @Override
    public void validate(Long value) {
        boolean isBookReserve = bookReservationHistoryRepository.existsByBookIdAndStatus(
                value,
                BookStatus.RESERVATION);

        if (isBookReserve) {
            throw new ValidationException("book.reserve", "book", "book_id", value);
        }
    }

    @Override
    public ValidatorType type() {
        return BOOK_RESERVATION;
    }
}
