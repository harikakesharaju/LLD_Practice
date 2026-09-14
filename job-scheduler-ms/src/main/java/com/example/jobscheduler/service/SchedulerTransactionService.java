package com.example.jobscheduler.service;

import com.example.jobscheduler.entity.*;
import com.example.jobscheduler.repository.JobExecutionRepository;
import com.example.jobscheduler.repository.JobRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.scheduling.support.CronExpression;

@Service
public class SchedulerTransactionService {

    private final JobRepository jobRepository;
    private final JobExecutionRepository executionRepository;

    public SchedulerTransactionService(
            JobRepository jobRepository,
            JobExecutionRepository executionRepository) {

        this.jobRepository = jobRepository;
        this.executionRepository = executionRepository;
    }

    @Transactional
    public void scheduleOne(UUID jobId) {

        // 1. Lock the job row
        Job job = jobRepository.findByIdForUpdate(jobId)
                .orElse(null);

        if (job == null) {
            return;
        }

        // 2. Make sure it is still eligible
        if (job.getStatus() != JobStatus.ACTIVE) {
            return;
        }

        if (job.getNextExecutionAt() == null) {
            return;
        }

        Instant now = Instant.now();

        // 3. Another scheduler may have already processed it
        if (job.getNextExecutionAt().isAfter(now)) {
            return;
        }

        // This is the occurrence we are scheduling
        Instant occurrence = job.getNextExecutionAt();

        // 4. Create JobExecution
        JobExecution execution = new JobExecution(
                UUID.randomUUID(),
                job.getId(),
                job.getJobType(),
                job.getPayloadJson(),
                occurrence,
                job.getMaxRetries(),
                now
        );

        executionRepository.save(execution);

        // 5. Advance the schedule
        if (job.getScheduleType() == ScheduleType.ONE_TIME) {

            job.setNextExecutionAt(null);
            job.setStatus(JobStatus.COMPLETED);

        } else {

            CronExpression cron =
                    CronExpression.parse(job.getCronExpression());

            ZonedDateTime occurrenceInZone =
                    occurrence.atZone(
                            ZoneId.of(job.getTimeZone())
                    );

            ZonedDateTime next =
                    cron.next(occurrenceInZone);

            if (next == null) {
                job.setNextExecutionAt(null);
                job.setStatus(JobStatus.COMPLETED);
            } else {
                job.setNextExecutionAt(next.toInstant());
            }
        }

        job.setUpdatedAt(now);
    }
}