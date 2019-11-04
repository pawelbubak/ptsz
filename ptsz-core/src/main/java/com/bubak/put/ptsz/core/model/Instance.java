package com.bubak.put.ptsz.core.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Instance {
    private List<Task> tasks = new ArrayList<>();
    private int tasksQuantity;

    public Instance(List<Task> tasks) {
        this.tasks = tasks;
        this.tasksQuantity = tasks.size();
    }

    public Task getTask(int task) {
        if (tasks == null) {
            return null;
        }
        return tasks.get(task);
    }

    public int getRealTasksQuantity() {
        return tasks != null ? tasks.size() : 0;
    }

    public Optional<Task> findTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }
}
