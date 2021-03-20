package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BaseTaskDTO;
import com.bookcrossing.api.job.JobType;

public interface BaseTaskService {

    BaseTaskDTO createTask(JobType job);

    BaseTaskDTO persist(BaseTaskDTO task);

}
