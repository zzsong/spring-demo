package com.zss.demo.batch.nestedjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import java.time.LocalDateTime;

@Configuration
public class ChildJob1Config {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step childJob1Step1(){
        return stepBuilderFactory.get("childJob1Step1").tasklet(new PrintTasklet()).build();
    }

    @Bean
    public Job childJob1(){
        return jobBuilderFactory.get("childJob1").start(childJob1Step1()).build();
    }


    private class PrintTasklet implements Tasklet {
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            System.out.println(chunkContext.getStepContext().getStepName()+"========"+Thread.currentThread().getName()+"===="+ LocalDateTime.now());
            return RepeatStatus.FINISHED;
        }
    }

}
