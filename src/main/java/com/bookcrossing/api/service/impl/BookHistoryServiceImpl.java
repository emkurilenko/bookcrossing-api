package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.BookHistoryDTO;
import com.bookcrossing.api.domain.dto.BookUserHistoryDTO;
import com.bookcrossing.api.domain.dto.book.BookDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.dto.search.UserHistorySearch;
import com.bookcrossing.api.domain.dto.user.UserDTO;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.mapper.BookUserHistoryMapper;
import com.bookcrossing.api.domain.repository.BookHistoryRepository;
import com.bookcrossing.api.service.BookHistoryService;
import com.bookcrossing.api.service.UserService;
import com.bookcrossing.api.service.search.SearchService;
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

    private final BaseMapper<BookHistoryDTO, BookHistory> mapper;
    private final UserService userService;
    private final BookHistoryRepository bookHistoryRepository;
    private final BookUserHistoryMapper bookUserHistoryMapper;
    private final SearchService<UserHistorySearch, List<BookHistoryDTO>> userHistorySearchSearchService;

    @Autowired
    public BookHistoryServiceImpl(
            final BaseMapper<BookHistoryDTO, BookHistory> mapper,
            final UserService userService,
            final BookHistoryRepository bookHistoryRepository,
            final BookUserHistoryMapper bookUserHistoryMapper,
            final SearchService<UserHistorySearch, List<BookHistoryDTO>> userHistorySearchSearchService) {
        super(mapper, bookHistoryRepository);
        this.mapper = mapper;
        this.userService = userService;
        this.bookHistoryRepository = bookHistoryRepository;
        this.bookUserHistoryMapper = bookUserHistoryMapper;
        this.userHistorySearchSearchService = userHistorySearchSearchService;
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
                .map(mapper::mapToDTO)
                .orElse(null); //todo thow excepton
    }

    @Override
    public BookHistoryDTO findAvailableBookByBookId(Long bookId) {
        return bookHistoryRepository.findOne(QBH.book.id.eq(bookId)
                .and(QBH.status.eq(BookStatus.AVAILABLE)))
                .map(mapper::mapToDTO)
                .orElse(null);
    }

    @Override
    public BookHistoryDTO findByBookIdAndStatuses(Long bookId, List<BookStatus> statuses) {
        return bookHistoryRepository.findOne(QBH.book.id.eq(bookId).and(QBH.status.in(statuses)))
                .map(mapper::mapToDTO)
                .orElse(null);
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

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getUserHistory(BookSearch bookSearch) {
        List<BookHistoryDTO> bookHistory = getBookHistory(
                bookSearch,
                List.of(
                        BookStatus.AVAILABLE,
                        BookStatus.TAKE_AWAY,
                        BookStatus.TAKEN_AWAY,
                        BookStatus.RESERVED,
                        BookStatus.CANCELED));

        return bookHistory.stream()
                .map(item -> {
                    BookDTO book = item.getBook();
                    book.setStatus(item.getStatus());
                    return book;
                })
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<BookHistoryDTO> getUserBookedHistory(BookSearch bookSearch) {
        List<BookHistoryDTO> bookHistory = getBookHistory(bookSearch, List.of(BookStatus.BOOKED));
        return bookHistory.stream()
                .peek(item -> {
                    BookDTO book = item.getBook();
                    book.setStatus(item.getStatus());
                    item.setBook(book);
                })
                .collect(Collectors.toList());
    }

    private List<BookHistoryDTO> getBookHistory(BookSearch bookSearch, List<BookStatus> statuses) {
        UserDTO currentUser = userService.getCurrentUser();

        UserHistorySearch userHistorySearch = UserHistorySearch.builder()
                .bookSearch(bookSearch)
                .user(currentUser)
                .statuses(statuses)
                .build();

        return userHistorySearchSearchService.search(userHistorySearch);
    }

    @Override
    @Transactional
    public BookHistoryDTO persist(BookHistoryDTO value) {
        BookHistory bookHistory = mapper.mapToEntity(value);
        BookHistory persistedBookHistory = bookHistoryRepository.saveAndFlush(bookHistory);
        persistedBookHistory
                .getBook()
                .setBookHistories(List.of(persistedBookHistory)); //todo hotfix

        return mapper.mapToDTO(persistedBookHistory);
    }
}
