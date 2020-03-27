package com.zss.demo.batch.nestedjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import java.time.LocalDateTime;

@Configuration
public class NestedJobConfig {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job childJob1;

    @Inject
    private Job childJob2;

    /**
     * batch:
     *     job:
     *       names: nestedJob
     * 在配置文件中添加启动job
     * @param repository
     * @param transactionManager
     * @return
     */

    @Bean
    public Job nestedJob(JobRepository repository, PlatformTransactionManager transactionManager){
        return jobBuilderFactory.get("nestedJob")
                .start(childJob1S(repository, transactionManager))
                .next(childJob2S(repository, transactionManager)).build();
    }

    @Bean
    public Step childJob1S(JobRepository repository, PlatformTransactionManager transactionManager){
        return new JobStepBuilder(new StepBuilder("childJob1-step"))
                .job(childJob1)
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }
    @Bean
    public Step childJob2S(JobRepository repository, PlatformTransactionManager transactionManager){
        return new JobStepBuilder(new StepBuilder("childJob2-step"))
                .job(childJob2)
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }


    private class PrintTasklet implements Tasklet {
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            System.out.println(chunkContext.getStepContext().getStepName()+"========"+Thread.currentThread().getName()+"===="+ LocalDateTime.now());
            return RepeatStatus.FINISHED;
        }
    }

}
