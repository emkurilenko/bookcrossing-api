package com.bookcrossing.api.service.search.factory;

import com.querydsl.core.types.Predicate;

public interface PredicateFactory<SEARCH, ENTITY> {

    Predicate create(SEARCH search);
}
