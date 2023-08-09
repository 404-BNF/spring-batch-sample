package dev.sk.springbatchsamplev5upgrade.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

public class MyStepExecutionListener implements StepExecutionListener {
    @Override
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Step "+stepExecution.getStepName()+ " going to start");
    }

    @Override
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Step "+stepExecution.getStepName()+ " is completed.");
        return ExitStatus.COMPLETED;
    }
}
