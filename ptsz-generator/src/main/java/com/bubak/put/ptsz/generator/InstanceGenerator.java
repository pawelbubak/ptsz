package com.bubak.put.ptsz.generator;

import com.bubak.put.ptsz.core.Instance;
import com.bubak.put.ptsz.core.Task;
import com.bubak.put.ptsz.generator.util.RandomUtil;
import com.bubak.put.ptsz.generator.util.Util;

import java.util.ArrayList;
import java.util.List;

public class InstanceGenerator {
    private RandomUtil randomUtil;

    public InstanceGenerator() {
        this.randomUtil = new RandomUtil();
    }

    public Instance generate(int tasksQuantity) {
        List<Task> tasks = prepareTasksWithProcessingTime(tasksQuantity);
        prepareTasksReadyTimeAndDueDate(tasks);
        return new Instance(tasks);
    }

    private List<Task> prepareTasksWithProcessingTime(int tasksQuantity){
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < tasksQuantity; i++) {
            tasks.add(createTask());
        }
        return tasks;
    }

    private void prepareTasksReadyTimeAndDueDate(List<Task> tasks) {
        int avgProcessingTime = Util.calculateAverageProcessingTime(tasks);
        int maxStartTime = Util.calculateMaxTaskStartTime(avgProcessingTime, tasks.size());

        for(Task task: tasks) {
            task.setReadyTime(randomUtil.randomTaskStartTime(maxStartTime, avgProcessingTime));
            task.setDueDate(randomUtil.randomTaskEndTime(task.getReadyTime(), task.getProcessingTime()));
        }
    }

    private Task createTask() {
        Task task = new Task();
        task.setProcessingTime(randomUtil.randomTaskDuration());
        return task;
    }
}
