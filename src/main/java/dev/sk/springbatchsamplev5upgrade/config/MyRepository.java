package dev.sk.springbatchsamplev5upgrade.config;

import dev.sk.springbatchsamplev5upgrade.dao.MyExecutionContextDAO;
import dev.sk.springbatchsamplev5upgrade.dao.MyJobExecutionDAO;
import dev.sk.springbatchsamplev5upgrade.dao.MyJobInstanceDAO;
import dev.sk.springbatchsamplev5upgrade.dao.MyStepExecutionDAO;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.stereotype.Component;

@Component
public class MyRepository extends SimpleJobRepository {
    /**
     * Recreating Map___DAO classes which we have before Spring Batch 5.X
     */
    private static final MyJobInstanceDAO jobInstanceDAO = new MyJobInstanceDAO();
    private static final MyJobExecutionDAO jobExecutionDAO = new MyJobExecutionDAO();
    private static final MyStepExecutionDAO stepExecutionDAO = new MyStepExecutionDAO();
    private static final MyExecutionContextDAO executionContextDAO = new MyExecutionContextDAO();

    public MyRepository(){
        super(jobInstanceDAO, jobExecutionDAO, stepExecutionDAO, executionContextDAO);
    }
    public MyRepository(JobInstanceDao jobInstanceDao, JobExecutionDao jobExecutionDao, StepExecutionDao stepExecutionDao, ExecutionContextDao ecDao) {

        super(null, null, null, null);
    }

}
