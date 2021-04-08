package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.BugReportDTO;
import com.bookcrossing.api.domain.entity.BugReport;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugReportService extends DefaultBaseService<BugReportDTO, BugReport, Long> {

    @Autowired
    public BugReportService(
            BaseMapper<BugReportDTO, BugReport> mapper,
            BaseCrudRepository<BugReport, Long> repository) {
        super(mapper, repository);
    }
}
