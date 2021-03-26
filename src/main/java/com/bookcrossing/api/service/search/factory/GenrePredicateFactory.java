package com.bookcrossing.api.service.search.factory;

import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.entity.Genre;
import com.bookcrossing.api.domain.entity.QGenre;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class GenrePredicateFactory implements PredicateFactory<BaseNamedSearch> {
    private static final QGenre QG = QGenre.genre;

    @Override
    public Predicate create(BaseNamedSearch search) {
        String name = search.getName();
        BooleanBuilder bb = new BooleanBuilder(QG.isDeleted.eq(Boolean.FALSE));
        if (StringUtils.hasText(name)) {
            bb.and(QG.name.contains(name));
        }
        return bb;
    }
}
