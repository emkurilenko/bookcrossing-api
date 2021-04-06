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
            log.error("BaseTask: {}, error: ",this.jobName(), e);
            task.setDescription(e.getLocalizedMessage());
            task.setStatus(Status.FAILED);
        }
        BaseTaskDTO persist = baseTaskService.persist(task);
        log.info(
                "Job: {} finished with status: {}, descr: {}",
                this.jobName(),
                persist.getStatus(),
                persist.getDescription());
    }
}
