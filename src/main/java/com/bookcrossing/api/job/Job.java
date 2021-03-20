package com.bookcrossing.api.job;

public interface Job {

    void schedule();

    boolean toExecute();

    JobType jobName();
}
