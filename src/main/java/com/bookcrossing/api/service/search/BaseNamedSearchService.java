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

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BaseNamedSearchService<DTO, ENTITY extends BaseEntity<ID>, ID, T extends BaseNamedSearch>
        implements SearchService<T, List<DTO>> {

    private final PredicateFactory<T, ENTITY> predicateFactory;
    private final BaseCrudRepository<ENTITY, ID> repository;
    private final BaseMapper<DTO, ENTITY> mapper;

    @Override
    @Transactional(readOnly = true)
    public List<DTO> search(T searchHelper) {
        Predicate predicate = predicateFactory.create(searchHelper);
        return StreamSupportUtils.toStream(repository.findAll(predicate))
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
