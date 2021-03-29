package com.bookcrossing.api.service;

import com.bookcrossing.api.domain.dto.BaseTaskDTO;
import com.bookcrossing.api.domain.entity.BaseTask;
import com.bookcrossing.api.job.JobType;

public interface BaseTaskService extends BaseService<BaseTaskDTO, Long> {

    BaseTaskDTO createTask(JobType job);

}
