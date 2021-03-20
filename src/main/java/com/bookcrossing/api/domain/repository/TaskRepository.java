package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BaseTask;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseCrudRepository<BaseTask, Long> {
}
