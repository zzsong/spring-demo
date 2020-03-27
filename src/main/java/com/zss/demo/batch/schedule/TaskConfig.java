package com.zss.demo.batch.schedule;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import java.time.LocalDateTime;

@Configuration
public class TaskConfig {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    @Inject
    private JobLauncher jobLauncher;


    @Scheduled(fixedDelay = 5000)
    public void scheduled() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        System.out.println("-------------------"+ LocalDateTime.now());
        JobParameters jobParameter = new JobParametersBuilder()
                .addString("time",String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        JobExecution execution = jobLauncher.run(runTaskJob(),jobParameter);
        System.out.println(execution.getStatus());

    }

    @Bean
    public Job runTaskJob(){
        return jobBuilderFactory.get("runTaskJob").start(oneTaskStep()).build();
    }

    @Bean
    public Step oneTaskStep() {
        return stepBuilderFactory.get("oneTaskStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                Object obj = chunkContext.getStepContext().getJobParameters().get("time");
                System.out.println("=========oneTaskStep=========="+obj);
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
