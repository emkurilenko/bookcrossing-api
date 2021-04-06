package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.BaseTaskDTO;
import com.bookcrossing.api.domain.entity.BaseTask;
import com.bookcrossing.api.domain.entity.BaseTask.Status;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;
import com.bookcrossing.api.job.JobType;
import com.bookcrossing.api.service.BaseTaskService;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseTaskServiceImpl extends DefaultBaseService<BaseTaskDTO, BaseTask, Long>
        implements BaseTaskService {

    @Autowired
    public BaseTaskServiceImpl(
            BaseMapper<BaseTaskDTO, BaseTask> mapper,
            BaseCrudRepository<BaseTask, Long> repository) {
        super(mapper, repository);
    }

    @Override
    @Transactional
    public BaseTaskDTO createTask(JobType job) {
        BaseTaskDTO baseTask = BaseTaskDTO.builder()
                .name(job.name())
                .status(Status.RUNNING)
                .createdDate(ZonedDateTime.now())
                .build();
        return persist(baseTask);
    }

}
