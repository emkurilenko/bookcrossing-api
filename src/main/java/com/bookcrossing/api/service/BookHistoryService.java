package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.BookUserHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.entity.BookStatus;

import java.util.List;

public interface BookHistoryService extends BaseService<BookHistoryDTO, Long> {

    List<BookUserHistoryDTO> findByUserId(Long userId);

    BookHistoryDTO findByBookIdAndUserId(Long bookId, Long userId);

    BookHistoryDTO findAvailableBookByBookId(Long bookId);

    BookHistoryDTO findByBookIdAndStatuses(Long bookId, List<BookStatus> statuses);

    List<BookHistoryDTO> findByBookId(Long bookId);

    List<BookDTO> getUserHistory(BookSearch bookSearch);

    List<BookHistoryDTO> getUserBookedHistory(BookSearch bookSearch);
}
