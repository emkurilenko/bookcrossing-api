package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.Location;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends BaseCrudRepository<Location, Long> {

    Optional<Location> findByBookId(Long bookId);
}
