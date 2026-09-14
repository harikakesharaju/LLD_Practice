package com.example.jobscheduler.service;

import com.example.jobscheduler.entity.JobExecution;
import com.example.jobscheduler.job.IJob;
import com.example.jobscheduler.job.JobContext;
import com.example.jobscheduler.job.JobRegistry;
import com.example.jobscheduler.job.JobResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WorkerService {

    private final WorkerTransactionService transactionService;
    private final JobRegistry jobRegistry;

    public WorkerService(
            WorkerTransactionService transactionService,
            JobRegistry jobRegistry) {

        this.transactionService = transactionService;
        this.jobRegistry = jobRegistry;
    }

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            concurrency = "${app.worker.concurrency:3}"
    )
    public void consume(String message) {

        UUID executionId = UUID.fromString(message);

        System.out.println(
                "Worker received execution: " + executionId
        );

        JobExecution execution =
                transactionService.claim(executionId);

        if (execution == null) {

            System.out.println(
                    "Execution already claimed/completed: "
                            + executionId
            );

            return;
        }

        try {

            System.out.println(
                    "Executing job: "
                            + execution.getJobType()
            );

            IJob job =
                    jobRegistry.get(execution.getJobType());

            JobContext context =
                    new JobContext(
                            execution.getId(),
                            execution.getJobId(),
                            execution.getPayloadSnapshot(),
                            execution.getAttempt()
                    );


            JobResult result = job.execute(context);

            if (result.success()) {

                transactionService.complete(
                        executionId,
                        result.result()
                );

            } else {

                transactionService.fail(
                        executionId,
                        result.error()
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            transactionService.fail(
                    executionId,
                    e.getMessage()
            );
        }
    }
}