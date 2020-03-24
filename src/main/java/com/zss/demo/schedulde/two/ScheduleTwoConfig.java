package com.zss.demo.schedulde.two;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 任务内容另外bean处理
 * 定义工作任务，引用使用那个bean的任务中的方法运行
 * 配置触发工作任务机制
 * 添加任务调度者进行对触发、工作任务进行管理。
 */
@Configuration
@EnableScheduling
public class ScheduleTwoConfig {

    @Autowired
    private MyJob myJob;

    //配置工作任务
    //实例一次，一直使用
    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetBeanName("myJob");
//        factoryBean.setTargetObject(myJob);
        factoryBean.setTargetMethod("run");
        factoryBean.setArguments("parameter", RandomStringUtils.randomAlphabetic(5));
        return factoryBean;
    }

    //调度，触发机制
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        factoryBean.setCronExpression("*/3 * * * * ? ");

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("triggerData", RandomStringUtils.randomAlphabetic(5));
        factoryBean.setJobDataMap(jobDataMap);
        return factoryBean;
    }

    //调度对象
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers( cronTriggerFactoryBean().getObject());
        return factoryBean;
    }
}
