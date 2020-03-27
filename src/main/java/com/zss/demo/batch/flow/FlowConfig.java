package com.zss.demo.batch.flow;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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

import javax.inject.Inject;

@Configuration
public class FlowConfig {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("flow-one-start").start(flowOne()).next(twoStep()).end().build();
    }

    @Bean
    public Step oneStep() {
        return stepBuilderFactory.get("oneStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=========oneStep==========");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step twoStep() {
        return stepBuilderFactory.get("twoStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=========twoStep==========");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Flow flowOne(){
        return new FlowBuilder<Flow>("flow-one").start(oneStep()).next(thirdStep()).build();
    }

    @Bean
    public Step thirdStep() {
        return stepBuilderFactory.get("twoStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=========thirdStep==========");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
