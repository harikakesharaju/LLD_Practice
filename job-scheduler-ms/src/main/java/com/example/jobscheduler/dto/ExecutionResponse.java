package com.example.jobscheduler.dto;
import com.example.jobscheduler.entity.ExecutionStatus;
import java.time.Instant;
import java.util.UUID;
public record ExecutionResponse(UUID id,UUID jobId,String jobType,String payloadSnapshot,Instant scheduledAt,
                                ExecutionStatus status,int attempt,int maxRetries,boolean published,
                                Instant startedAt,Instant completedAt,String result,String errorMessage) {}
