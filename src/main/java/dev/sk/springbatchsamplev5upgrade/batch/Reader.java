package dev.sk.springbatchsamplev5upgrade.batch;


import com.opencsv.CSVReader;
import dev.sk.springbatchsamplev5upgrade.model.Person;
import dev.sk.springbatchsamplev5upgrade.service.FileUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.format.DateTimeFormatter;

@Component
@StepScope
public class Reader implements ItemReader<Person>, StepExecutionListener {

    @Autowired
    FileUtils fileUtils;

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Person person = fileUtils.read();
        if (person!=null){
            System.out.println("Readed Line: "+person);
        }
        return person;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Going to start Reader::");
        try {
            fileUtils.openReader();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Finished Reader::");

        try {
            fileUtils.closeReader();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return stepExecution.getExitStatus();
    }
}
