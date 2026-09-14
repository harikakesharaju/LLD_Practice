package com.example.jobscheduler.repository;

import com.example.jobscheduler.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.*;

public interface JobExecutionRepository extends JpaRepository<JobExecution,UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from JobExecution e where e.id=:id")
    Optional<JobExecution> findByIdForUpdate(@Param("id") UUID id);

    @Query("select e from JobExecution e where e.status=:status and e.published=false order by e.createdAt")
    List<JobExecution> findUnpublished(@Param("status") ExecutionStatus status,Pageable pageable);

    Page<JobExecution> findByJobIdOrderByScheduledAtDesc(UUID jobId,Pageable pageable);
}
