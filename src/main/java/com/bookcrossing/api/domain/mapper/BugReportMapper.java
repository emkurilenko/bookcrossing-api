package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.dto.BugReportDTO;
import com.bookcrossing.api.domain.entity.BugReport;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BugReportMapper extends BaseMapper<BugReportDTO, BugReport> {
}
