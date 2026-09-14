package com.example.jobscheduler.job;
public record JobResult(boolean success,String result,String error) {
    public static JobResult success(String result){return new JobResult(true,result,null);}
    public static JobResult failure(String error){return new JobResult(false,null,error);}
}
