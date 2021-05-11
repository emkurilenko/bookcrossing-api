package com.bookcrossing.api.service.search.factory;

import static java.util.Optional.ofNullable;

import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.dto.search.UserHistorySearch;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserHistoryPredicateFactory implements PredicateFactory<UserHistorySearch> {

    private static final QBookHistory QBH = QBookHistory.bookHistory;

    @Override
    public Predicate create(UserHistorySearch search) {
        Long userId = ofNullable(search.getUser())
                .map(BaseEntityDTO::getId)
                .orElseThrow(() -> new IllegalStateException("user.does.not.present"));

        BookSearch bookSearch = search.getBookSearch();
        String name = bookSearch.getName();
        List<Long> authors = bookSearch.getAuthors();
        List<Long> genres = bookSearch.getGenres();

        BooleanExpression bb = QBH.user.id.eq(userId)
                .and(QBH.status.in(search.getStatuses()))
                .and(QBH.book.isDeleted.eq(Boolean.FALSE));

        if (StringUtils.hasText(name)) {
            bb.and(QBH.book.name.containsIgnoreCase(name));
        }
        if (authors != null && !authors.isEmpty()) {
            bb.and(QBH.book.authors.any().id.in(authors));
        }
        if (genres != null && !genres.isEmpty()) {
            bb.and(QBH.book.genres.any().id.in(genres));
        }
        return bb;
    }
}
