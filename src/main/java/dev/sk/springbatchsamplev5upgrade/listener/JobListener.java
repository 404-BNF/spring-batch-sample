package dev.sk.springbatchsamplev5upgrade.listener;

import dev.sk.springbatchsamplev5upgrade.config.BatchConfig;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class JobListener implements JobExecutionListener {
    @Autowired
    BatchConfig config;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job is going to Start...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job Completed...");
        System.out.println(config.filelist);
        config.filelist.clear();
    }
}
