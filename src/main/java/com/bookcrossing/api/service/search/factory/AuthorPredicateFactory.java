package com.bookcrossing.api.service.search.factory;

import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.entity.Author;
import com.bookcrossing.api.domain.entity.QAuthor;
import com.bookcrossing.api.domain.entity.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthorPredicateFactory implements PredicateFactory<BaseNamedSearch> {
    private static final QAuthor QA = QAuthor.author;

    @Override
    public Predicate create(BaseNamedSearch search) {
        String name = search.getName();
        BooleanBuilder bb = new BooleanBuilder(QA.isDeleted.eq(Boolean.FALSE));
        if (StringUtils.hasText(name)) {
            bb.and(QA.name.contains(name));
        }
        return bb;
    }
}
