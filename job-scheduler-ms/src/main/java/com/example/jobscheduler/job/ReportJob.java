package com.example.jobscheduler.job;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class ReportJob implements IJob {
    public String getType(){return "REPORT";}
    public JobResult execute(JobContext context) throws Exception {
        TimeUnit.MILLISECONDS.sleep(500);
        return JobResult.success("Report generated. Payload="+context.payloadJson());
    }
}
