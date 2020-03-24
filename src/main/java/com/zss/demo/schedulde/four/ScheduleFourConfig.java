package com.zss.demo.schedulde.four;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class ScheduleFourConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(()->{
            System.out.println("run===>"+Thread.currentThread().getName()+" ---==>"+ LocalDateTime.now());
        },triggerContext -> {
            String cron = "*/5 * * * * ?";
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });
    }
}
