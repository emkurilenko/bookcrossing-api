package com.bookcrossing.api.service.search;

import com.bookcrossing.api.domain.dto.search.BaseNamedSearch;
import com.bookcrossing.api.domain.entity.BaseEntity;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;
import com.bookcrossing.api.service.search.factory.PredicateFactory;
import com.bookcrossing.api.utils.StreamSupportUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BaseNamedSearchService<DTO, ENTITY extends BaseEntity<ID>, ID> implements SearchService<BaseNamedSearch, List<DTO>> {

    private final PredicateFactory<BaseNamedSearch> predicateFactory;
    private final BaseCrudRepository<ENTITY, ID> repository;
    private final BaseMapper<DTO, ENTITY> mapper;

    @Override
    public List<DTO> search(BaseNamedSearch searchHelper) {
        Predicate predicate = predicateFactory.create(searchHelper);
        return StreamSupportUtils.toStream(repository.findAll(predicate))
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
