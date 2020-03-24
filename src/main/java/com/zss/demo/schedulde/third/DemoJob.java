package com.zss.demo.schedulde.third;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DemoJob extends QuartzJobBean {

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        run(context.getMergedJobDataMap());
    }

    public void run(JobDataMap dataMap){
        StringBuffer buffer = new StringBuffer();
        dataMap.forEach((k,v)->{buffer.append(";").append(k).append("=").append(v);});
        String out = String.format("demoJob--run--=[%s]==>%s. \t ->%s",Thread.currentThread().getName(),buffer.substring(1), LocalDateTime.now());
        System.out.println(out);
    }
}
