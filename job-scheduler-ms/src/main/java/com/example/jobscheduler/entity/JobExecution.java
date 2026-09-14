package com.example.jobscheduler.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="job_executions",
       uniqueConstraints=@UniqueConstraint(name="uk_job_scheduled",columnNames={"job_id","scheduled_at"}),
       indexes=@Index(name="idx_execution_pending",columnList="status,published"))
public class JobExecution {
    @Id private UUID id;
    @Column(name="job_id",nullable=false) private UUID jobId;
    @Column(name="job_type",nullable=false) private String jobType;
    @Column(name="payload_snapshot",nullable=false,columnDefinition="TEXT") private String payloadSnapshot;
    @Column(name="scheduled_at",nullable=false) private Instant scheduledAt;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ExecutionStatus status;
    @Column(nullable=false) private int attempt;
    @Column(nullable=false) private int maxRetries;
    @Column(nullable=false) private boolean published;
    private Instant startedAt;
    private Instant completedAt;
    @Lob @Column(columnDefinition="TEXT") private String result;
    @Lob @Column(name="error_message",columnDefinition="TEXT") private String errorMessage;
    @Column(nullable=false) private Instant createdAt;
    @Column(nullable=false) private Instant updatedAt;

    protected JobExecution() {}
    public JobExecution(UUID id,UUID jobId,String jobType,String payloadSnapshot,Instant scheduledAt,
                         int maxRetries,Instant now) {
        this.id=id; this.jobId=jobId; this.jobType=jobType; this.payloadSnapshot=payloadSnapshot;
        this.scheduledAt=scheduledAt; this.status=ExecutionStatus.PENDING; this.attempt=0;
        this.maxRetries=maxRetries; this.published=false; this.createdAt=now; this.updatedAt=now;
    }
    public UUID getId(){return id;} public UUID getJobId(){return jobId;} public String getJobType(){return jobType;}
    public String getPayloadSnapshot(){return payloadSnapshot;} public Instant getScheduledAt(){return scheduledAt;}
    public ExecutionStatus getStatus(){return status;} public int getAttempt(){return attempt;}
    public int getMaxRetries(){return maxRetries;} public boolean isPublished(){return published;}
    public Instant getStartedAt(){return startedAt;} public Instant getCompletedAt(){return completedAt;}
    public String getResult(){return result;} public String getErrorMessage(){return errorMessage;}
    public void setPublished(boolean v){published=v;} public void setStatus(ExecutionStatus v){status=v;}
    public void setAttempt(int v){attempt=v;} public void setStartedAt(Instant v){startedAt=v;}
    public void setCompletedAt(Instant v){completedAt=v;} public void setResult(String v){result=v;}
    public void setErrorMessage(String v){errorMessage=v;} public void setUpdatedAt(Instant v){updatedAt=v;}
}
