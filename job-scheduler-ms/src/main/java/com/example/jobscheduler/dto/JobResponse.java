package com.example.jobscheduler.dto;
import com.example.jobscheduler.entity.*;
import java.time.Instant;
import java.util.UUID;
public record JobResponse(UUID id,String name,String jobType,String payloadJson,ScheduleType scheduleType,
                          String cronExpression,String timeZone,Instant startAt,Instant nextExecutionAt,
                          JobStatus status,int maxRetries) {}
