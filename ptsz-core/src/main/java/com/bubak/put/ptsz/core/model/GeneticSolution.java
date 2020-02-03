package com.bubak.put.ptsz.core.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GeneticSolution {
    @NonNull
    private List<Task> tasks;
    private int delay;

    public GeneticSolution(GeneticSolution solution) {
        tasks = new ArrayList<>(solution.getTasks());
        delay = solution.delay;
    }

    public int size() {
        return tasks != null ? tasks.size() : 0;
    }

    public int getDelay() {
        return delay;
    }

    public void calculateDelay() {
        List<Integer> machines = Arrays.asList(0, 0, 0, 0);
        delay = 0;
        for (Task task : tasks) {
            Integer endTime = Collections.min(machines);
            int index = machines.indexOf(endTime);
            endTime = Math.max(endTime, task.getReadyTime()) + task.getProcessingTime();
            machines.set(index, endTime);
            delay +=  Math.max(0, endTime - task.getDueDate());
        }
    }
}
