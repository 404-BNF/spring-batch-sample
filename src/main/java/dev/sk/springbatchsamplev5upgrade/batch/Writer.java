package dev.sk.springbatchsamplev5upgrade.batch;

import dev.sk.springbatchsamplev5upgrade.config.BatchConfig;
import dev.sk.springbatchsamplev5upgrade.model.Person;
import dev.sk.springbatchsamplev5upgrade.service.FileUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@StepScope
public class Writer implements ItemWriter<Person>, StepExecutionListener {
    @Autowired
    FileUtils fileUtils;

    @Autowired
    BatchConfig config;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Going to start Writer::");
        try {
            fileUtils.openWriter();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Finished Writer::");
        try {
            fileUtils.closeWriter();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        List<? extends  Person> list = chunk.getItems();
        for (Person person : list){
            fileUtils.write(person);
            config.filelist.add(person.toString());
        }
        System.out.println("Wrote Line: "+list);
    }
}
