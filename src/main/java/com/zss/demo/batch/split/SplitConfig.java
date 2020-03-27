package com.zss.demo.batch.split;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.inject.Inject;
import java.time.LocalDateTime;

@Configuration
public class SplitConfig {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("start-split-job")
                .start(splitFlow1())
                .split(new SimpleAsyncTaskExecutor())
                .add(splitFlow2())
                .end().build();
    }

    @Bean
    public Flow splitFlow1(){
        return new FlowBuilder<Flow>("splitFlow1")
                .start(stepBuilderFactory.get("step-one").tasklet(new PrintTasklet()).build())
                .build();
    }

    @Bean
    public Flow splitFlow2(){
        return new FlowBuilder<Flow>("splitFlow2")
                .start(stepBuilderFactory.get("step-two").tasklet(new PrintTasklet()).build())
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
