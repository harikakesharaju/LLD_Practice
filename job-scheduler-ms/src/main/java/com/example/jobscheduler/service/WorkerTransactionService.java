package com.example.jobscheduler.service;

import com.example.jobscheduler.entity.ExecutionStatus;
import com.example.jobscheduler.entity.JobExecution;
import com.example.jobscheduler.repository.JobExecutionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class WorkerTransactionService {

    private final JobExecutionRepository executionRepository;

    public WorkerTransactionService(
            JobExecutionRepository executionRepository) {

        this.executionRepository = executionRepository;
    }

    @Transactional
    public JobExecution claim(UUID executionId) {

        JobExecution execution =
                executionRepository.findByIdForUpdate(executionId)
                        .orElse(null);

        if (execution == null) {
            return null;
        }

        // Another worker already claimed or completed it
        if (execution.getStatus() != ExecutionStatus.PENDING) {
            return null;
        }

        execution.setStatus(ExecutionStatus.RUNNING);
        execution.setStartedAt(Instant.now());
        execution.setAttempt(execution.getAttempt() + 1);

        return execution;
    }

    @Transactional
    public void complete(
            UUID executionId,
            String result) {

        JobExecution execution =
                executionRepository.findByIdForUpdate(executionId)
                        .orElse(null);

        if (execution == null) {
            return;
        }

        execution.setStatus(ExecutionStatus.SUCCESS);
        execution.setResult(result);
        execution.setCompletedAt(Instant.now());
    }

    @Transactional
    public void fail(
            UUID executionId,
            String errorMessage) {

        JobExecution execution =
                executionRepository.findByIdForUpdate(executionId)
                        .orElse(null);

        if (execution == null) {
            return;
        }

        execution.setStatus(ExecutionStatus.FAILED);
        execution.setErrorMessage(errorMessage);
        execution.setCompletedAt(Instant.now());
    }
}