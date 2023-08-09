package dev.sk.springbatchsamplev5upgrade.controller;

//import dev.sk.springbatchsamplev5upgrade.config.MyJobLauncher;
import dev.sk.springbatchsamplev5upgrade.config.MyRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@RestController
public class MainController {
    @Autowired
//    JobLauncher jobLauncher;
    TaskExecutorJobLauncher jobLauncher;

    @Autowired
    MyRepository myRepository;

    @Autowired
    Job myJob;

    Logger log = Logger.getLogger(getClass().getName());

    @GetMapping
    String root(){
        return "Spring Batch sample implementation using V5";
    }

    @GetMapping("/batch")
    public void executeBatchJob(){
//        ((SimpleJobLauncher)jobLauncher).setJobRepository(myRepository);
        jobLauncher.setJobRepository(myRepository);
        log.info("Entered Execute Batch JOB...............");
        System.out.println("Current DateTime: " + LocalDateTime.now());
        try{
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time",System.currentTimeMillis())
                    .addString("input_file","input/input.csv")
                    .addString("output_file","output/output.csv")
                    .addString("date_format","MM/d/yyy")
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(myJob, jobParameters);
            System.out.println("Job Status: "+jobExecution.getStatus());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
