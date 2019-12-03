package com.bubak.put.ptsz.generator.algorithm.algorithms;

import com.bubak.put.ptsz.core.model.*;
import com.bubak.put.ptsz.generator.algorithm.Algorithm;

import java.util.Comparator;
import java.util.List;

public class AdvancedSortAlgorithm implements Algorithm {
    private Solution solution;

    @Override
    public Solution solve(Problem problem) {
        solution = problem.prepareSolution();
        assignTasks(problem.getInstance());
        solution.calculateDelay();
        return solution;
    }

    private void assignTasks(Instance instance) {
        List<Task> tasks = instance.getTasks();
        while (!tasks.isEmpty()) {
            Machine machine = solution.getLowLoadedMachine();
            Task task = tasks.stream()
                    .min(Comparator.comparingDouble(value -> {
                        Task val = (Task) value;
                        return Math.max(val.getReadyTime() - machine.getLastEndTime(), 0);
                    }).thenComparingInt(value -> {
                        Task val = (Task) value;
                        return val.getProcessingTime();
                    }).thenComparingInt(value -> {
                        Task val = (Task) value;
                        return val.getDueDate();
                    }).thenComparingDouble(value -> {
                        Task val = (Task) value;
                        return -val.getProcessingTime() * 1.0 / (val.getDueDate() - val.getReadyTime());
                    })).orElse(null);
            machine.addTask(task);
            tasks.remove(task);
        }
    }
}
