package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BookReservationHistory;
import com.bookcrossing.api.domain.entity.BookStatus;

import org.springframework.stereotype.Repository;

@Repository
public interface BookReservationHistoryRepository extends BaseCrudRepository<BookReservationHistory, Long> {
    boolean existsByBookIdAndUserIdAndStatus(Long bookId, Long userId, BookStatus status);
    boolean existsByBookIdAndStatus(Long bookId, BookStatus status);
}
