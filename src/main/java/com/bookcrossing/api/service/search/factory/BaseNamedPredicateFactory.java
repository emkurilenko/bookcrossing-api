package com.bookcrossing.api.service.search.factory;

import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.entity.QBaseNamedEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BaseNamedPredicateFactory implements PredicateFactory<BaseNamedSearch> {

    private static final QBaseNamedEntity QBNE = QBaseNamedEntity.baseNamedEntity;

    @Override
    public Predicate create(BaseNamedSearch search) {
        String name = search.getName();
        BooleanBuilder bb = new BooleanBuilder(QBNE.isDeleted.eq(Boolean.FALSE));
        if (StringUtils.hasText(name)) {
            bb.and(QBNE.name.contains(name));
        }
        return bb;
    }
}
