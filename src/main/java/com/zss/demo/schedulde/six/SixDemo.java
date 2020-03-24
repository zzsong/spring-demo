package com.zss.demo.schedulde.six;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class SixDemo   implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ScheduleSixConfig scheduleSixConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)  {
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        {
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            new Thread(()->{scheduleSixConfig.addTriggerTask("demo-1"
                    ,new TriggerTask(()->{
                        System.out.println(Thread.currentThread().getName()+"=====demo-1====>"+ LocalDateTime.now());
                    },new CronTrigger("*/5 * * * * ?")));}).start();

            new Thread(()->{scheduleSixConfig.addTriggerTask("demo-2"
                    ,new TriggerTask(()->{
                        System.err.println(Thread.currentThread().getName()+"=====demo-2====>"+ LocalDateTime.now());
                    },new CronTrigger("*/3 * * * * ?")));}).start();

            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=================进入修改、删除任务=============================");
            new Thread(()->{scheduleSixConfig.cancelTriggerTask("demo-2");}).start();


            new Thread(()->{scheduleSixConfig.resetTriggerTask("demo-1"
                    ,new TriggerTask(()->{
                        System.out.println(Thread.currentThread().getName()+"=====demo-4xxxx====>"+ LocalDateTime.now());
                    },new CronTrigger("*/6 * * * * ?")));}).start();

        }
    }
}
