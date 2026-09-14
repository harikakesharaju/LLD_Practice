package com.example.jobscheduler.service;

import com.example.jobscheduler.dto.*;
import com.example.jobscheduler.entity.*;
import com.example.jobscheduler.job.JobRegistry;
import com.example.jobscheduler.repository.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.support.CronExpression;
import java.time.*;
import java.util.*;

@Service
public class JobService {
    private final JobRepository repository;
    private final JobRegistry registry;
    private final ObjectMapper objectMapper;

    public JobService(JobRepository repository,JobRegistry registry,ObjectMapper objectMapper){
        this.repository=repository;this.registry=registry;this.objectMapper=objectMapper;
    }

    @Transactional
    public JobResponse create(CreateJobRequest r){
        registry.get(r.jobType());
        validate(r);
        Instant now=Instant.now();
        Instant first=calculateFirstExecution(r);
        Job job=new Job(UUID.randomUUID(),r.name(),r.jobType(),r.payloadJson(),r.scheduleType(),
                r.cronExpression(),r.timeZone(),r.startAt(),first,JobStatus.ACTIVE,r.maxRetries(),now,now);
        repository.save(job);
        return toResponse(job);
    }

    private void validate(CreateJobRequest r){
        try{objectMapper.readTree(r.payloadJson());}
        catch(Exception e){throw new IllegalArgumentException("payloadJson must be valid JSON");}
        if(r.scheduleType()==ScheduleType.CRON){
            if(r.cronExpression()==null||r.cronExpression().isBlank())
                throw new IllegalArgumentException("cronExpression is required for CRON");
            if(r.timeZone()==null||r.timeZone().isBlank())
                throw new IllegalArgumentException("timeZone is required for CRON");
            try{ZoneId.of(r.timeZone());CronExpression.parse(r.cronExpression());}
            catch(Exception e){throw new IllegalArgumentException("Invalid cronExpression or timeZone");}
        }
    }

    private Instant calculateFirstExecution(CreateJobRequest r){
        if(r.scheduleType()==ScheduleType.ONE_TIME)return r.startAt();
        ZoneId zone=ZoneId.of(r.timeZone());
        CronExpression cron=CronExpression.parse(r.cronExpression());
        ZonedDateTime next=cron.next(r.startAt().atZone(zone).minusNanos(1));
        if(next==null)throw new IllegalArgumentException("Cron has no next execution");
        return next.toInstant();
    }

    @Transactional
    public void pause(UUID id){
        Job j=repository.findByIdForUpdate(id).orElseThrow();
        j.setStatus(JobStatus.PAUSED);j.setUpdatedAt(Instant.now());
    }

    @Transactional
    public void resume(UUID id){
        Job j=repository.findByIdForUpdate(id).orElseThrow();
        if(j.getStatus()==JobStatus.COMPLETED)throw new IllegalStateException("Completed job cannot be resumed");
        j.setStatus(JobStatus.ACTIVE);j.setUpdatedAt(Instant.now());
    }

    public JobResponse get(UUID id){return toResponse(repository.findById(id).orElseThrow());}

    public Set<String> supportedTypes(){return registry.supportedTypes();}

    private JobResponse toResponse(Job j){
        return new JobResponse(j.getId(),j.getName(),j.getJobType(),j.getPayloadJson(),j.getScheduleType(),
                j.getCronExpression(),j.getTimeZone(),j.getStartAt(),j.getNextExecutionAt(),j.getStatus(),j.getMaxRetries());
    }
}
