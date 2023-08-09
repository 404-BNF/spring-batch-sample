package dev.sk.springbatchsamplev5upgrade.batch;

import dev.sk.springbatchsamplev5upgrade.model.Person;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@StepScope
public class Processor implements ItemProcessor<Person, Person>, StepExecutionListener {
    @Override
    public Person process(Person person) throws Exception {
        int age =  (int) ChronoUnit.YEARS.between(person.getDob(), LocalDate.now());
        person.setAge(age);
        System.out.println("Processed Line: "+person);
        return person;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Going to start Processor::");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Finished Processor::");
        return null;
    }


}
