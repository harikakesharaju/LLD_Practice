package com.example.jobscheduler.service;

import com.example.jobscheduler.entity.ExecutionStatus;
import com.example.jobscheduler.entity.JobExecution;
import com.example.jobscheduler.repository.JobExecutionRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

@Component
public class ExecutionPublisher {

    private final JobExecutionRepository executionRepository;
    private final ExecutionPublisherTransactionService transactionService;

    private final boolean enabled;
    private final int batchSize;

    public ExecutionPublisher(
            JobExecutionRepository executionRepository,
            ExecutionPublisherTransactionService transactionService,
            @Value("${app.publisher.enabled:true}") boolean enabled,
            @Value("${app.publisher.batch-size:100}") int batchSize) {

        this.executionRepository = executionRepository;
        this.transactionService = transactionService;
        this.enabled = enabled;
        this.batchSize = batchSize;
    }

    @Scheduled(fixedDelayString = "${app.publisher.poll-ms:1000}")
    public void publishPending() {

        if (!enabled) {
            return;
        }

        List<JobExecution> executions =
                executionRepository.findUnpublished(
                        ExecutionStatus.PENDING,
                        PageRequest.of(0, batchSize)
                );

        for (JobExecution execution : executions) {

            UUID executionId = execution.getId();

            transactionService.publishOne(executionId);
        }
    }
}