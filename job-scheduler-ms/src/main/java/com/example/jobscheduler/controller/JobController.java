package com.example.jobscheduler.controller;
import com.example.jobscheduler.dto.*;
import com.example.jobscheduler.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService service;
    public JobController(JobService service){this.service=service;}

    @PostMapping
    public ResponseEntity<JobResponse> create(@Valid @RequestBody CreateJobRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
    @GetMapping("/{id}") public JobResponse get(@PathVariable UUID id){return service.get(id);}
    @PostMapping("/{id}/pause") public ResponseEntity<Void> pause(@PathVariable UUID id){service.pause(id);return ResponseEntity.noContent().build();}
    @PostMapping("/{id}/resume") public ResponseEntity<Void> resume(@PathVariable UUID id){service.resume(id);return ResponseEntity.noContent().build();}
    @GetMapping("/supported-types") public Set<String> supportedTypes(){return service.supportedTypes();}
}
