package com.bubak.put.ptsz.core.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        if (tasks != null) {
            tasks.add(task);
        }
    }

    public int getTasksQuantity() {
        return tasks != null ? tasks.size() : 0;
    }

    public int getTasksSchedulingDelay() {
        int processingEndTime = 0;
        int schedulingDelay = 0;
        for (Task task : tasks) {
            processingEndTime = Math.max(processingEndTime, task.getReadyTime()) + task.getProcessingTime();
            schedulingDelay += Math.max(0, processingEndTime - task.getDueDate());
        }
        return schedulingDelay;
    }
}
