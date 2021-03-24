package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.BookBookedHistoryDTO;
import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookBookingDTO;
import com.bookcrossing.api.domain.entity.BookBookingHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.mapper.BookReservationMapper;
import com.bookcrossing.api.domain.repository.BookBookingHistoryRepository;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.BookBookingHistoryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookBookingHistoryServiceImpl extends DefaultBaseService<BookBookedHistoryDTO, BookBookingHistory, Long>
        implements BookBookingHistoryService {

    private final BookReservationMapper bookReservationMapper;
    private final BookHistoryService bookHistoryService;
    private final BookBookingHistoryRepository bookBookedHistoryRepository;

    public BookBookingHistoryServiceImpl(
            final BaseMapper<BookBookedHistoryDTO, BookBookingHistory> mapper,
            final BookBookingHistoryRepository bookBookedHistoryRepository,
            final BookReservationMapper bookReservationMapper,
            final BookHistoryService bookHistoryService) {
        super(mapper, bookBookedHistoryRepository);
        this.bookBookedHistoryRepository = bookBookedHistoryRepository;
        this.bookReservationMapper = bookReservationMapper;
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    @Transactional
    public BookBookingDTO reserveBook(BookBookedHistoryDTO reservationHistoryDTO) {
        BookBookedHistoryDTO persist = persist(reservationHistoryDTO);
        return bookReservationMapper.mapToDTO(persist);
    }

    @Override
    @Transactional
    public void cancelReservingBook(Long bookId, Long userId) {
        BookBookingHistory reservedHistory = bookBookedHistoryRepository
                .findByBookIdAndUserIdAndStatus(
                        bookId,
                        userId,
                        BookStatus.BOOKED)
                .orElseThrow(() -> new IllegalStateException("not.found.a.book.in.reserved.history"));

        reservedHistory.setStatus(BookStatus.CANCELED);
        bookBookedHistoryRepository.save(reservedHistory);

        BookHistoryDTO bookHistoryDTO = bookHistoryService.mapToDto(reservedHistory.getHistory());
        bookHistoryService.cancelBookBooking(bookHistoryDTO);
    }

    @Override
    public BookBookedHistoryDTO findByBookIdAndUserId(Long bookId, Long userId) {
        return bookBookedHistoryRepository
                .findByBookIdAndUserIdAndStatus(bookId, userId, BookStatus.RESERVED)
                .map(this::mapToDto)
                .orElse(null);
    }

}
