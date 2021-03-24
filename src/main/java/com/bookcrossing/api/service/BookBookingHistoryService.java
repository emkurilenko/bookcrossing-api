package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BookBookedHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookBookingDTO;
import com.bookcrossing.api.domain.entity.BookBookingHistory;

public interface BookBookingHistoryService extends BaseService<BookBookedHistoryDTO, BookBookingHistory, Long> {

    BookBookingDTO reserveBook(BookBookedHistoryDTO value);

    void cancelReservingBook(Long bookId, Long userId);

    BookBookedHistoryDTO findByBookIdAndUserId(Long bookId, Long userId);

}
