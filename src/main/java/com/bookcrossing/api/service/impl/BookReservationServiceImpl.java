package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.config.ApplicationSetting;
import com.bookcrossing.api.domain.dto.BookReservationHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookReservationDTO;
import com.bookcrossing.api.domain.entity.BookReservationHistory;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.mapper.BookReservationMapper;
import com.bookcrossing.api.domain.repository.BookReservationHistoryRepository;
import com.bookcrossing.api.service.BookReservationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookReservationServiceImpl extends DefaultBaseService<BookReservationHistoryDTO, BookReservationHistory, Long>
        implements BookReservationService {

    private final BookReservationMapper bookReservationMapper;

    public BookReservationServiceImpl(
            BaseMapper<BookReservationHistoryDTO, BookReservationHistory> mapper,
            BookReservationHistoryRepository bookReservationHistoryRepository,
            BookReservationMapper bookReservationMapper) {
        super(mapper, bookReservationHistoryRepository);
        this.bookReservationMapper = bookReservationMapper;
    }

    @Override
    @Transactional
    public BookReservationDTO reserveBook(BookReservationHistoryDTO reservationHistoryDTO) {
        BookReservationHistoryDTO persist = persist(reservationHistoryDTO);
        return bookReservationMapper.mapToDTO(persist);
    }
}
