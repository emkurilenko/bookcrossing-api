package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.BugReport;

import org.springframework.stereotype.Repository;

@Repository
public interface BugReportRepository extends BaseCrudRepository<BugReport, Long> {
}
