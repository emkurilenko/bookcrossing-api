package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BookHistory;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryRepository extends BaseCrudRepository<BookHistory, Long> {
    List<BookHistory> findAllByBookId(Long bookId);
}
