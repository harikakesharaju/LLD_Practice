package com.example.jobscheduler.job;
import java.util.UUID;
public record JobContext(UUID executionId,UUID jobId,String payloadJson,int attempt) {}
