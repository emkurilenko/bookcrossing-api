package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.Location;

import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends BaseCrudRepository<Location, Long> {
}
