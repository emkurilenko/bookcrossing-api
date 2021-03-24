package com.bookcrossing.api.validator;

import static com.bookcrossing.api.validator.ValidatorType.BOOK_BOOKING;

import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
import com.bookcrossing.api.exception.ValidationException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookReservationValidator implements Validator<Long> {

    private final BookHistoryRepository bookHistoryRepository;

    @Autowired
    public BookReservationValidator(final BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    @Override
    public void validate(Long value) {
        Optional<BookHistory> isBookBooked = bookHistoryRepository.findByBookIdAndStatus(
                value,
                BookStatus.BOOKED);

        if (isBookBooked.isPresent()) {
            throw new ValidationException("book.reserve", "book", "book_id", value);
        }
    }

    @Override
    public ValidatorType type() {
        return BOOK_BOOKING;
    }
}
