package com.zss.demo.schedulde.third;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@EnableScheduling
public class ScheduleThirdConfig {

    @Autowired
    private DemoJob demoJob;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobData", RandomStringUtils.randomAlphabetic(5));
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(demoJob.getClass());
//        factoryBean.setBeanName("demoJob");
        factoryBean.setJobDataMap(jobDataMap);
        return factoryBean;
    }

    //随机数取第一次，因为只实例一次使用。
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        JobDataMap triggerDataMap = new JobDataMap();
        triggerDataMap.put("triggerData", RandomStringUtils.randomAlphabetic(5));
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        factoryBean.setJobDataMap(triggerDataMap);
        factoryBean.setCronExpression("*/5 * * * * ?");
        return factoryBean;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(cronTriggerFactoryBean().getObject());
        return factoryBean;
    }

}
