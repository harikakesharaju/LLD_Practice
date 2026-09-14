package com.example.jobscheduler.job;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JobRegistry {
    private final Map<String,IJob> jobs;
    public JobRegistry(List<IJob> implementations){
        Map<String,IJob> m=new HashMap<>();
        for(IJob job:implementations) {
            if(m.put(job.getType(),job)!=null) throw new IllegalStateException("Duplicate job type: "+job.getType());
        }
        jobs=Map.copyOf(m);
    }
    public IJob get(String type){
        IJob job=jobs.get(type);
        if(job==null) throw new IllegalArgumentException("Unsupported job type: "+type);
        return job;
    }
    public Set<String> supportedTypes(){return jobs.keySet();}
}
