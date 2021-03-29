package com.bookcrossing.api.service.search.factory;

import com.bookcrossing.api.domain.dto.search.LocationSearch;
import com.bookcrossing.api.domain.entity.QLocation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LocationPredicateFactory implements PredicateFactory<LocationSearch> {

    private static final QLocation QL = QLocation.location;

    @Override
    public Predicate create(LocationSearch locationSearch) {
        BooleanBuilder bb = new BooleanBuilder(QL.isDeleted.eq(Boolean.FALSE));
        String address = locationSearch.getAddress();
        if (StringUtils.hasText(address)) {
            bb.and(QL.address.containsIgnoreCase(address));
        }

        return bb;
    }
}
