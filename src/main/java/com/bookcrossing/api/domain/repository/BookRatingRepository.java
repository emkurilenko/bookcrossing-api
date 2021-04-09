package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BookRating;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends BaseCrudRepository<BookRating, Long> {
}
