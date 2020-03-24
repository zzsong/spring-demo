package com.zss.demo.schedulde.one;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class ScheduleOneConfig {

    @Scheduled(cron = "*/5 * * * * * ")
    public void one(){
        System.out.println("=*/5 * * * * * *====="+ LocalDateTime.now());
    }
}
