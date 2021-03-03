package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseCrudRepository<BE extends BaseEntity, ID> extends JpaRepository<BE, ID>,
        QuerydslPredicateExecutor<BE> {
}
