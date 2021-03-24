package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.BookUserHistoryDTO;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;

import java.util.List;

public interface BookHistoryService extends BaseService<BookHistoryDTO, BookHistory, Long> {

    List<BookUserHistoryDTO> findByUserId(Long userId);

    BookHistoryDTO findByBookIdAndUserId(Long bookId, Long userId);

    BookHistoryDTO findAvailableBookByBookId(Long bookId);

    BookHistoryDTO findByBookIdAndStatuses(Long bookId, List<BookStatus> statuses);

    BookHistoryDTO cancelBookBooking(BookHistoryDTO history);

    List<BookHistoryDTO> findByBookId(Long bookId);
}
