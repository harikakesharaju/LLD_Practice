package com.example.jobscheduler.job;
public interface IJob {
    String getType();
    JobResult execute(JobContext context) throws Exception;
}
