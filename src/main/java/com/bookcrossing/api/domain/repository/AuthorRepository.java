package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.Author;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends BaseCrudRepository<Author, Long> {
}
