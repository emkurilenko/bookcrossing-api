package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BaseNamedEntityDTO;
import com.bookcrossing.api.domain.entity.BaseNamedEntity;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class DefaultBaseService<DTO extends BaseNamedEntityDTO<ID>,
        ENTITY extends BaseNamedEntity<ID>,
        ID extends Serializable> implements BaseService<DTO, ENTITY, ID> {

    private final BaseMapper<DTO, ENTITY> mapper;
    private final BaseCrudRepository<ENTITY, ID> repository;

    @Override
    @Transactional
    public DTO persist(DTO value) {
        ENTITY entity = mapToEntity(value);
        ENTITY persisted = repository.saveAndFlush(entity);
        return mapToDto(persisted);
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(); //todo throw exception
    }

    @Override
    @Transactional
    public void remove(ID id) {
        DTO byId = findById(id);
        ENTITY entity = mapToEntity(byId);
        entity.setIsDeleted(true);
        repository.saveAndFlush(entity);
    }

    @Override
    public ENTITY mapToEntity(DTO value) {
        return mapper.mapToEntity(value);
    }

    @Override
    public DTO mapToDto(ENTITY value) {
        return mapper.mapToDTO(value);
    }
}