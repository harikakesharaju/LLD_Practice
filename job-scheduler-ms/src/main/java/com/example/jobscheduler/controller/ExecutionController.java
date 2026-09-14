package com.example.jobscheduler.controller;
import com.example.jobscheduler.dto.ExecutionResponse;
import com.example.jobscheduler.entity.JobExecution;
import com.example.jobscheduler.repository.JobExecutionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/executions")
public class ExecutionController {
    private final JobExecutionRepository repository;
    public ExecutionController(JobExecutionRepository repository){this.repository=repository;}

    @GetMapping("/{id}")
    public ExecutionResponse get(@PathVariable UUID id){return toResponse(repository.findById(id).orElseThrow());}

    @GetMapping("/job/{jobId}")
    public List<ExecutionResponse> byJob(@PathVariable UUID jobId,@RequestParam(defaultValue="50") int limit){
        int safe=Math.max(1,Math.min(limit,200));
        return repository.findByJobIdOrderByScheduledAtDesc(jobId,PageRequest.of(0,safe))
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private ExecutionResponse toResponse(JobExecution e){
        return new ExecutionResponse(e.getId(),e.getJobId(),e.getJobType(),e.getPayloadSnapshot(),
                e.getScheduledAt(),e.getStatus(),e.getAttempt(),e.getMaxRetries(),e.isPublished(),
                e.getStartedAt(),e.getCompletedAt(),e.getResult(),e.getErrorMessage());
    }
}
