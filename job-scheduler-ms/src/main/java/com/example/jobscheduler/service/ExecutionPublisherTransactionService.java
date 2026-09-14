package com.example.jobscheduler.service;

import com.example.jobscheduler.entity.JobExecution;
import com.example.jobscheduler.repository.JobExecutionRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ExecutionPublisherTransactionService {

    private final JobExecutionRepository executionRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public ExecutionPublisherTransactionService(
            JobExecutionRepository executionRepository,
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.topic}") String topic) {

        this.executionRepository = executionRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Transactional
    public void publishOne(UUID executionId) {

        JobExecution execution =
                executionRepository.findByIdForUpdate(executionId)
                        .orElse(null);

        if (execution == null) {
            return;
        }

        if (execution.isPublished()) {
            return;
        }

        kafkaTemplate.send(
                topic,
                execution.getId().toString(),
                execution.getId().toString()
        );

        execution.setPublished(true);
    }
}