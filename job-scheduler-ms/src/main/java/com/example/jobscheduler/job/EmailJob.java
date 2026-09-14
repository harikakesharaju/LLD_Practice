package com.example.jobscheduler.job;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class EmailJob implements IJob {
    public String getType(){return "EMAIL";}
    public JobResult execute(JobContext context) throws Exception {
        TimeUnit.MILLISECONDS.sleep(300);
        return JobResult.success("Email sent. Payload="+context.payloadJson());
    }
}
