package com.zss.demo.schedulde.two;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyJob {

    @Autowired
    private JobExecutionContext jobExecutionContext;

    public void run(String arg, String random){
        System.out.println(jobExecutionContext);
        String out = String.format("myJob->run==>[%s],=>[%s]--->%s",arg,random, LocalDateTime.now());
        System.out.println(out);
    }
}
