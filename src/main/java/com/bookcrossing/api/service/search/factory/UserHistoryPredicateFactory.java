package com.bookcrossing.api.service.search.factory;

import static java.util.Optional.ofNullable;

import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import com.bookcrossing.api.domain.dto.search.BookSearch;
import com.bookcrossing.api.domain.dto.search.UserHistorySearch;
import com.bookcrossing.api.domain.entity.QBookHistory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

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
        String author = bookSearch.getAuthor();
        String genre = bookSearch.getGenre();

        BooleanExpression bb = QBH.user.id.eq(userId)
                .and(QBH.status.in(search.getStatuses()))
                .and(QBH.book.isDeleted.eq(Boolean.FALSE));

        if (StringUtils.hasText(name)) {
            bb.and(QBH.book.name.containsIgnoreCase(name));
        }
        if (StringUtils.hasText(author)) {
            bb.and(QBH.book.authors.any().name.containsIgnoreCase(author));
        }
        if (StringUtils.hasText(genre)) {
            bb.and(QBH.book.genres.any().name.containsIgnoreCase(genre));
        }
        return bb;
    }
}
