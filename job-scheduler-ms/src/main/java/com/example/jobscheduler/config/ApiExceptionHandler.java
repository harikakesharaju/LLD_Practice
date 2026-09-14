package com.example.jobscheduler.config;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<Map<String,String>> notFound(Exception e){
        return ResponseEntity.status(404).body(Map.of("error","Resource not found"));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Map<String,String>> bad(Exception e){
        return ResponseEntity.badRequest().body(Map.of("error",String.valueOf(e.getMessage())));
    }
    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<Map<String,String>> conflict(Exception e){
        return ResponseEntity.status(409).body(Map.of("error",String.valueOf(e.getMessage())));
    }
}
