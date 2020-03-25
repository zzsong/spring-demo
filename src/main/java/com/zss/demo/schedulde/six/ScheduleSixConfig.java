package com.zss.demo.schedulde.six;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Configuration
@EnableScheduling
public class ScheduleSixConfig implements SchedulingConfigurer {

    private ScheduledTaskRegistrar taskRegistrar;

    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
    }

    /**
     * 添加任务
     * @param taskId
     * @param triggerTask
     */
    public void addTriggerTask(String taskId, TriggerTask triggerTask){
        if (taskFutures.containsKey(taskId)) {
            throw new SchedulingException("the taskId[" + taskId + "] was added.");
        }
        TaskScheduler taskScheduler = taskRegistrar.getScheduler();

        ScheduledFuture<?> future = taskScheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        taskFutures.put(taskId, future);
    }

    /**
     * 取消任务
     * @param taskId
     */
    public void cancelTriggerTask(String taskId){
        ScheduledFuture future = taskFutures.get(taskId);
        if (future != null)
        {
            future.cancel(true);
        }
        taskFutures.remove(taskId);
    }

    /**
     * 重置任务
     * @param taskId
     * @param triggerTask
     */
    public void resetTriggerTask(String taskId, TriggerTask triggerTask){
        cancelTriggerTask(taskId);
        addTriggerTask(taskId, triggerTask);
    }
    /**
     * 任务编号
     *
     * @return
     */
    public Set<String> taskIds()
    {
        return taskFutures.keySet();
    }

    /**
     * 是否存在任务
     *
     * @param taskId
     * @return
     */
    public boolean hasTask(String taskId)
    {
        return this.taskFutures.containsKey(taskId);
    }
}
