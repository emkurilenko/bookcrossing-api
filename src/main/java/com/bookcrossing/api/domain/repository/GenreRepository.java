package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.Genre;

import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends BaseCrudRepository<Genre, Long> {
}
