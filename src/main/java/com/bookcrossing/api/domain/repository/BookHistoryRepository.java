package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryRepository extends BaseCrudRepository<BookHistory, Long> {
    List<BookHistory> findAllByBookId(Long bookId);

    Optional<BookHistory> findByBookIdAndStatus(Long bookId, BookStatus status);
}
