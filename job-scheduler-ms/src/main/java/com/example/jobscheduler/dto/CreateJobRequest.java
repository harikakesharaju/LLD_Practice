package com.example.jobscheduler.dto;
import com.example.jobscheduler.entity.ScheduleType;
import jakarta.validation.constraints.*;
import java.time.Instant;

public record CreateJobRequest(
    @NotBlank String name,
    @NotBlank String jobType,
    @NotBlank String payloadJson,
    @NotNull ScheduleType scheduleType,
    String cronExpression,
    String timeZone,
    @NotNull Instant startAt,
    @Min(0) int maxRetries
) {}
