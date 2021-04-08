package com.bookcrossing.api.controller;

import com.bookcrossing.api.domain.dto.BugReportDTO;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bug-report")
public class BugReportController {

    private final BaseService<BugReportDTO, Long> service;

    @Autowired
    public BugReportController(BaseService<BugReportDTO, Long> service) {
        this.service = service;
    }

    @PostMapping
    public BugReportDTO createBugReport(@RequestBody BugReportDTO bug) {
        return service.persist(bug);
    }
}
