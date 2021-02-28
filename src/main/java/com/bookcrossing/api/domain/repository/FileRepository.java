package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.File;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends BaseCrudRepository<File, UUID>{
}
