package com.bookcrossing.api.service.search.factory;

import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBook;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BookPredicateFactory implements PredicateFactory<BookSearch, Book> {

    private static final QBook QB = QBook.book;
    private static final QBookHistory QBH = QBookHistory.bookHistory;

    @Override
    public Predicate create(BookSearch search) {
        String name = search.getName();
        String author = search.getAuthor();
        String genre = search.getGenre();
        BooleanBuilder bb = new BooleanBuilder(QB.isDeleted.eq(Boolean.FALSE));
        if (StringUtils.hasText(name)) {
            bb.and(QB.name.containsIgnoreCase(name));
        }
        if (StringUtils.hasText(author)) {
            bb.and(QB.authors.any().name.containsIgnoreCase(author));
        }
        if (StringUtils.hasText(genre)) {
            bb.and(QB.genres.any().name.containsIgnoreCase(genre));
        }

        bb.and(QB.bookHistories.any().status.eq(BookStatus.AVAILABLE));
        return bb;
    }
}
