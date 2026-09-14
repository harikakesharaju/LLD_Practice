package com.example.jobscheduler.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="jobs", indexes=@Index(name="idx_jobs_due", columnList="status,next_execution_at"))
public class Job {
    @Id private UUID id;
    @Column(nullable=false) private String name;
    @Column(name="job_type", nullable=false) private String jobType;
    @Column(nullable=false, columnDefinition="TEXT") private String payloadJson;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ScheduleType scheduleType;
    private String cronExpression;
    private String timeZone;
    private Instant startAt;
    @Column(name="next_execution_at") private Instant nextExecutionAt;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private JobStatus status;
    @Column(nullable=false) private int maxRetries;
    @Column(nullable=false) private Instant createdAt;
    @Column(nullable=false) private Instant updatedAt;
    @Version private long version;

    protected Job() {}
    public Job(UUID id,String name,String jobType,String payloadJson,ScheduleType scheduleType,
               String cronExpression,String timeZone,Instant startAt,Instant nextExecutionAt,
               JobStatus status,int maxRetries,Instant createdAt,Instant updatedAt) {
        this.id=id; this.name=name; this.jobType=jobType; this.payloadJson=payloadJson;
        this.scheduleType=scheduleType; this.cronExpression=cronExpression; this.timeZone=timeZone;
        this.startAt=startAt; this.nextExecutionAt=nextExecutionAt; this.status=status;
        this.maxRetries=maxRetries; this.createdAt=createdAt; this.updatedAt=updatedAt;
    }
    public UUID getId(){return id;} public String getName(){return name;} public String getJobType(){return jobType;}
    public String getPayloadJson(){return payloadJson;} public ScheduleType getScheduleType(){return scheduleType;}
    public String getCronExpression(){return cronExpression;} public String getTimeZone(){return timeZone;}
    public Instant getStartAt(){return startAt;} public Instant getNextExecutionAt(){return nextExecutionAt;}
    public JobStatus getStatus(){return status;} public int getMaxRetries(){return maxRetries;}
    public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
    public void setNextExecutionAt(Instant v){nextExecutionAt=v;} public void setStatus(JobStatus v){status=v;}
    public void setUpdatedAt(Instant v){updatedAt=v;}
}
