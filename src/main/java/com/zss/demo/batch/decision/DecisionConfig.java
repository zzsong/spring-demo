package com.zss.demo.batch.decision;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import java.time.LocalDateTime;

@Configuration
public class DecisionConfig {

    @Inject
    private JobBuilderFactory jobBuilderFactory;

    @Inject
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public JobExecutionDecider decider(){
        return new MyDecider();
    }

    @Bean
    public Job startJob(){
        return jobBuilderFactory.get("decision-job-start")
                .start(oneStep())
                .next(decider())
                .from(decider()).on("EVEN").to(evenStep())
                .from(decider()).on("ODD").to(oddStep())
                .from(oddStep()).on("*").to(decider()) //* 表示任何返回值都 执行
                .end().build();
    }

    @Bean
    public Step oneStep() {
        return stepBuilderFactory.get("oneStep").tasklet(new PrintTasklet()).build();
    }

    @Bean
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep").tasklet(new PrintTasklet()).build();
    }

    @Bean
    public Step evenStep() {
        return stepBuilderFactory.get("evenStep").tasklet(new PrintTasklet()).build();
    }


    private class PrintTasklet implements Tasklet {
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            System.out.println(chunkContext.getStepContext().getStepName()+"======"+Thread.currentThread().getName()+"===="+ LocalDateTime.now());
            return RepeatStatus.FINISHED;
        }
    }

}
