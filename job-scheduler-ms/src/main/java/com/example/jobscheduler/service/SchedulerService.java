package com.example.jobscheduler.service;

import com.example.jobscheduler.entity.JobStatus;
import com.example.jobscheduler.repository.JobRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class SchedulerService {

    private final JobRepository jobRepository;
    private final SchedulerTransactionService transactionService;

    private final boolean enabled;
    private final int batchSize;

    public SchedulerService(
            JobRepository jobRepository,
            SchedulerTransactionService transactionService,
            @Value("${app.scheduler.enabled:true}") boolean enabled,
            @Value("${app.scheduler.batch-size:50}") int batchSize) {

        this.jobRepository = jobRepository;
        this.transactionService = transactionService;
        this.enabled = enabled;
        this.batchSize = batchSize;
    }

    @Scheduled(
            fixedDelayString = "${app.scheduler.poll-ms:1000}"
    )
    public void poll() {

        if (!enabled) {
            return;
        }

        Instant now = Instant.now();

        List<UUID> ids =
                jobRepository.findDueJobIds(
                        JobStatus.ACTIVE,
                        now,
                        PageRequest.of(0, batchSize)
                );

        for (UUID id : ids) {

            transactionService.scheduleOne(id);

        }
    }
}