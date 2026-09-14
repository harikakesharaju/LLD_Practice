package com.example.jobscheduler.repository;

import com.example.jobscheduler.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.time.Instant;
import java.util.*;

public interface JobRepository extends JpaRepository<Job,UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select j from Job j where j.id=:id")
    Optional<Job> findByIdForUpdate(@Param("id") UUID id);

    @Query("select j.id from Job j where j.status=:status and j.nextExecutionAt is not null and j.nextExecutionAt<=:now order by j.nextExecutionAt")
    List<UUID> findDueJobIds(@Param("status") JobStatus status,@Param("now") Instant now,Pageable pageable);
}
