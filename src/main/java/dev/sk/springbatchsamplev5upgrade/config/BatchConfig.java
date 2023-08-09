package dev.sk.springbatchsamplev5upgrade.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.sk.springbatchsamplev5upgrade.batch.Reader;
import dev.sk.springbatchsamplev5upgrade.batch.Writer;
import dev.sk.springbatchsamplev5upgrade.listener.JobListener;
import dev.sk.springbatchsamplev5upgrade.listener.MyStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
/**
 * Note:
 *   DefaultBatchConfigurer was removed in Spring Batch 5.X
 */
public class BatchConfig { //extends DefaultBatchConfigurer

    /*@Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(null);
    }*/

    public List<String> filelist = new ArrayList<>();

//    @Autowired
//    JobBuilderFactory jobBuilderFactory;

//    @Autowired
//    StepBuilderFactory stepBuilderFactory;

//    @Autowired
//    JobRepository jobRepository;

    @Autowired
    MyRepository jobRepository;
    @Autowired
    Reader reader;

    @Autowired
    ItemProcessor processor;

    @Autowired
    Writer writer;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.url}")
    String url;

    @Bean
    public Job myJob(){
        return new JobBuilder("My-Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(processCSV())
                .end().build();
    }

    @Bean
    @JobScope
    public Step processCSV() {
        System.out.println("JOB SCOPE=================================");
        return new StepBuilder("Step-CSV-Process", jobRepository)
                .chunk(5)
                 .reader(reader)
                 .processor(processor)
                 .writer(writer)
    //                .listener(listener())
                 .listener(stepListener())
                .transactionManager(new ResourcelessTransactionManager())
                 .build();
    }
    @Bean
    public JobListener listener(){
        return new JobListener();
    }
    public StepListener stepListener(){
        return new MyStepExecutionListener();
    }


}
