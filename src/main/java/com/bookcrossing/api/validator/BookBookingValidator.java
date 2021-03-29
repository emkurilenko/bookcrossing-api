package com.bookcrossing.api.validator;

import static com.bookcrossing.api.domain.entity.BookStatus.AVAILABLE;
import static com.bookcrossing.api.domain.entity.BookStatus.BOOKED;
import static com.bookcrossing.api.domain.entity.BookStatus.RESERVED;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKEN_AWAY;
import static com.bookcrossing.api.domain.entity.BookStatus.TAKE_AWAY;
import static com.bookcrossing.api.validator.ValidatorType.BOOK_BOOKING;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
import com.bookcrossing.api.exception.ValidationException;
import com.bookcrossing.api.service.UserService;
import com.bookcrossing.api.utils.AuthUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookBookingValidator implements Validator<Long> {

    public static final List<BookStatus> STATUSES = List.of(
            AVAILABLE,
            BOOKED,
            RESERVED,
            TAKE_AWAY,
            TAKEN_AWAY);

    private final AuthUtils authUtils;
    private final BookHistoryRepository bookHistoryRepository;

    @Autowired
    public BookBookingValidator(
            AuthUtils authUtils,
            final BookHistoryRepository bookHistoryRepository) {
        this.authUtils = authUtils;
        this.bookHistoryRepository = bookHistoryRepository;
    }

    @Override
    public void validate(Long value) {
        UserDTO currentUser = authUtils.getCurrentUser();
        List<BookHistory> allByBookId = bookHistoryRepository.findAllByBookId(value);

        boolean isOwner = allByBookId.stream()
                .anyMatch(isBookOwner(currentUser.getId()));

        if (isOwner) {
            throw new ValidationException("no.right", "book", "book_id", value);
        }

        Optional<BookHistory> isBookBooked = bookHistoryRepository.findByBookIdAndStatus(
                value,
                BOOKED);

        if (isBookBooked.isPresent()) {
            throw new ValidationException("book.reserve", "book", "book_id", value);
        }
    }

    private Predicate<BookHistory> isBookOwner(Long userId) {
        return item -> item.getUser().getId().equals(userId) && STATUSES.contains(item.getStatus());
    }

    @Override
    public ValidatorType type() {
        return BOOK_BOOKING;
    }
}
