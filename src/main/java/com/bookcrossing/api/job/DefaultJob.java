package com.bookcrossing.api.job;

import com.bookcrossing.api.domain.dto.BaseTaskDTO;
import com.bookcrossing.api.domain.entity.BaseTask.Status;
import com.bookcrossing.api.service.BaseTaskService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultJob implements Job {

    private final BaseTaskService baseTaskService;

    protected DefaultJob(BaseTaskService baseTaskService) {
        this.baseTaskService = baseTaskService;
    }

    @Override
    public void schedule() {
        log.info("Job {} started", this.jobName());
        BaseTaskDTO task = baseTaskService.createTask(this.jobName());
        try {
            Status status = this.toExecute() ? Status.FINISHED : Status.FAILED;
            task.setStatus(status);
        } catch (Exception e) {
            task.setDescription(e.getMessage());
            task.setStatus(Status.FAILED);
        }
        BaseTaskDTO persist = baseTaskService.persist(task);
        log.info("Job: {} finished with status: {}", this.jobName(), persist.getStatus());
    }
}
