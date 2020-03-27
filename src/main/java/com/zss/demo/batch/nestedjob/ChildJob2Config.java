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
public class ChildJob2Config {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step childJob2Step1(){
        return stepBuilderFactory.get("childJob2-Step1").tasklet(new PrintTasklet()).build();
    }
    @Bean
    public Step childJob2Step2(){
        return stepBuilderFactory.get("childJob2-Step2").tasklet(new PrintTasklet()).build();
    }
    @Bean
    public Job childJob2(){
        return jobBuilderFactory.get("childJob2")
                .start(childJob2Step1())
                .next(childJob2Step2()).build();
    }


    private class PrintTasklet implements Tasklet {
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            System.out.println(chunkContext.getStepContext().getStepName()+"========"+Thread.currentThread().getName()+"===="+ LocalDateTime.now());
            return RepeatStatus.FINISHED;
        }
    }

}
