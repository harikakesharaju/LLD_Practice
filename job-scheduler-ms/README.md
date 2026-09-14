# Simple Distributed Job Scheduler

This is the intentionally simple version for learning distributed job scheduling.

Flow:

Client
 -> JobController
 -> JobService
 -> PostgreSQL jobs

SchedulerService (@Scheduled)
 -> finds due jobs
 -> creates JobExecution(PENDING)
 -> advances nextExecutionAt

ExecutionPublisher (@Scheduled)
 -> finds PENDING unpublished executions
 -> Kafka topic `job-executions`

WorkerService (@KafkaListener)
 -> consumes execution ID
 -> atomically claims PENDING -> RUNNING
 -> finds IJob implementation
 -> executes it
 -> updates JobExecution

Why no Outbox table?
JobExecution itself contains `published=false`. The publisher retries unpublished executions.
If Kafka send succeeds but the DB update happens after a crash, Kafka can contain a duplicate message;
the worker's atomic state transition prevents the same execution from being executed twice.

This is still not exactly-once execution for arbitrary external side effects. Use executionId as an
idempotency key when a job calls an external service.

Run:
1. Java 21 + Maven
2. docker compose up -d
3. Import as Existing Maven Project in Eclipse
4. Run JobSchedulerApplication

One-time example:
POST http://localhost:8080/api/jobs
{
  "name": "Generate report",
  "jobType": "REPORT",
  "payloadJson": "{\"reportType\":\"sales\"}",
  "scheduleType": "ONE_TIME",
  "startAt": "2026-08-27T08:00:00Z",
  "maxRetries": 2
}

Cron example:
{
  "name": "Daily report",
  "jobType": "REPORT",
  "payloadJson": "{\"reportType\":\"sales\"}",
  "scheduleType": "CRON",
  "cronExpression": "0 0 10 * * *",
  "timeZone": "Asia/Kolkata",
  "startAt": "2026-08-27T00:00:00Z",
  "maxRetries": 2
}

Useful endpoints:
GET  /api/jobs/supported-types
GET  /api/jobs/{jobId}
POST /api/jobs/{jobId}/pause
POST /api/jobs/{jobId}/resume
GET  /api/executions/{executionId}
GET  /api/executions/job/{jobId}

Distributed test:
Build the jar, then start several copies:
java -jar target/job-scheduler-1.0.0.jar --server.port=8081
java -jar target/job-scheduler-1.0.0.jar --server.port=8082
java -jar target/job-scheduler-1.0.0.jar --server.port=8083

All use the same PostgreSQL and Kafka consumer group `job-workers`.
The topic has 6 partitions, so up to 6 worker instances can actively consume partitions concurrently.
