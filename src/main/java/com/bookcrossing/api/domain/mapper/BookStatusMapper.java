package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookHistory;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.repository.BookRepository;

import java.util.Comparator;
import java.util.List;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookStatusMapper {

    private static final List<BookStatus> ignoreStatuses = List.of();

    private final BookRepository bookRepository;

    @Autowired
    public BookStatusMapper(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Named("getLastStatus")
    @Transactional(readOnly = true)
    public BookStatus getLastStatus(Book book) {
        return bookRepository.findById(book.getId())
                .map(Book::getBookHistories)
                .orElseGet(List::of)
                .stream()
                .filter(item -> !ignoreStatuses.contains(item.getStatus()))
                .max(Comparator.comparing(BookHistory::getCreatedDate))
                .map(BookHistory::getStatus)
                .orElse(BookStatus.UNKNOWN);
    }
}
