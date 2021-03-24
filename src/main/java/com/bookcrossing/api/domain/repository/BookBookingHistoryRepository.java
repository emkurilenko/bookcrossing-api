package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BookBookingHistory;
import com.bookcrossing.api.domain.entity.BookStatus;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface BookBookingHistoryRepository extends BaseCrudRepository<BookBookingHistory, Long> {
    boolean existsByBookIdAndUserIdAndStatus(Long bookId, Long userId, BookStatus status);

    boolean existsByBookIdAndStatus(Long bookId, BookStatus status);

    Optional<BookBookingHistory> findByBookIdAndUserIdAndStatus(
            Long bookId,
            Long userId,
            BookStatus status);
}
