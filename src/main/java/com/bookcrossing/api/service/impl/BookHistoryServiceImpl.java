package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.BookUserHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.mapper.BookUserHistoryMapper;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.utils.StreamSupportUtils;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookHistoryServiceImpl extends DefaultBaseService<BookHistoryDTO, BookHistory, Long>
        implements BookHistoryService {

    private static final QBookHistory QBH = QBookHistory.bookHistory;

    private final BookHistoryRepository bookHistoryRepository;
    private final BookUserHistoryMapper bookUserHistoryMapper;

    @Autowired
    public BookHistoryServiceImpl(
            final BaseMapper<BookHistoryDTO, BookHistory> mapper,
            final BookHistoryRepository bookHistoryRepository,
            final BookUserHistoryMapper bookUserHistoryMapper) {
        super(mapper, bookHistoryRepository);
        this.bookHistoryRepository = bookHistoryRepository;
        this.bookUserHistoryMapper = bookUserHistoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookUserHistoryDTO> findByUserId(Long userId) {
        BooleanBuilder bb = new BooleanBuilder(QBH.user.id.eq(userId));

        Iterable<BookHistory> all = bookHistoryRepository.findAll(bb);

        return StreamSupportUtils.toStream(all)
                .map(bookUserHistoryMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookHistoryDTO findByBookIdAndUserId(Long bookId, Long userId) {
        return bookHistoryRepository.findOne(QBH.user.id.eq(userId)
                .and(QBH.book.id.eq(bookId)))
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public BookHistoryDTO findAvailableBookByBookId(Long bookId) {
        return bookHistoryRepository.findOne(QBH.book.id.eq(bookId)
                .and(QBH.status.eq(BookStatus.AVAILABLE)))
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public BookHistoryDTO findByBookIdAndStatuses(Long bookId, List<BookStatus> statuses) {
        return bookHistoryRepository.findOne(QBH.book.id.eq(bookId).and(QBH.status.in(statuses)))
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public BookHistoryDTO cancelBookBooking(BookHistoryDTO history) {
        history.setStatus(BookStatus.CANCELED);
        BookDTO book = history.getBook();

        BookHistory bookedBook = bookHistoryRepository.findOne(QBH.book.id.eq(book.getId())
                .and(QBH.status.eq(BookStatus.RESERVED)))
                .orElseThrow(() -> new IllegalStateException("not.found.reserved.book"));

        bookedBook.setStatus(BookStatus.AVAILABLE);

        bookHistoryRepository.save(bookedBook);
        return persist(history);
    }

    @Override
    public List<BookHistoryDTO> findByBookId(Long bookId) {
        return bookHistoryRepository.findAllByBookId(bookId)
                .stream()
                .map(item -> BookHistoryDTO.builder()
                        .id(item.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
