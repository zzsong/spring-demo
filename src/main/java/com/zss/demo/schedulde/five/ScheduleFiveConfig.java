package com.zss.demo.schedulde.five;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class ScheduleFiveConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(()->{
            System.out.println("run===>"+Thread.currentThread().getName()+" ---==>"+ LocalDateTime.now());
        },triggerContext -> {
            String cron = queryCron();
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });
    }

    public String queryCron(){
        String random = RandomStringUtils.randomNumeric(1);
        if (random.equals("0")) {
            random = "5";
        }
        System.out.println("====random=="+random);
        return String.format("*/%s * * * * ?",random);
    }
}
