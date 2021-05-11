package com.bookcrossing.api.service.search.factory;

import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.entity.BookStatus;
import com.bookcrossing.api.domain.entity.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BookPredicateFactory implements PredicateFactory<BookSearch> {

    private static final QBook QB = QBook.book;

    @Override
    public Predicate create(BookSearch search) {
        String name = search.getName();
        List<Long> authors = search.getAuthors();
        List<Long> genres = search.getGenres();
        List<Long> locationIds = search.getLocationIds();
        BooleanBuilder bb = new BooleanBuilder(QB.isDeleted.eq(Boolean.FALSE));
        if (StringUtils.hasText(name)) {
            bb.and(QB.name.containsIgnoreCase(name));
        }
        if (authors != null && !authors.isEmpty()) {
            bb.and(QB.authors.any().id.in(authors));
        }
        if (genres != null && !genres.isEmpty()) {
            bb.and(QB.genres.any().id.in(genres));
        }
        if (locationIds != null && !locationIds.isEmpty()) {
            bb.and(QB.location.id.in(locationIds));
        }
        bb.and(QB.bookHistories.any().status.eq(BookStatus.AVAILABLE));
        return bb;
    }
}
