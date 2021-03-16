package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BookReservationHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookReservationDTO;
import com.bookcrossing.api.domain.entity.BookReservationHistory;

public interface BookReservationService extends BaseService<BookReservationHistoryDTO, BookReservationHistory, Long> {

    BookReservationDTO reserveBook(BookReservationHistoryDTO reservationHistoryDTO);
}
