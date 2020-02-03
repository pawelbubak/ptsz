package com.bubak.put.ptsz.core.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    List<Task> tasks = new ArrayList<>();
    long lastEndTime = 0;
    long delay = 0;

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
            calculateNewEndTime(task);
            calculateDelay(task);
        }
    }

    void calculateEndTime() {
        lastEndTime = 0;
        tasks.forEach(this::calculateNewEndTime);
    }

    Task getTask(int index) {
        if (index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    public int getTasksQuantity() {
        return tasks != null ? tasks.size() : 0;
    }

    int getTasksSchedulingDelay() {
        int processingEndTime = 0;
        int schedulingDelay = 0;
        for (Task task : tasks) {
            processingEndTime = Math.max(processingEndTime, task.getReadyTime()) + task.getProcessingTime();
            schedulingDelay += Math.max(0, processingEndTime - task.getDueDate());
        }
        return schedulingDelay;
    }

    private void calculateNewEndTime(Task task) {
        lastEndTime = Math.max(lastEndTime, task.getReadyTime()) + task.getProcessingTime();
    }

    private void calculateDelay(Task task) {
        delay += Math.max(0, lastEndTime - task.getDueDate());
    }

    public void setTasks(List<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        }
    }
}
