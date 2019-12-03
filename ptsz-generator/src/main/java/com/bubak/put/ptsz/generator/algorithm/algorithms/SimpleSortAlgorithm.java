package com.bubak.put.ptsz.generator.algorithm.algorithms;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Task;
import com.bubak.put.ptsz.generator.algorithm.Algorithm;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleSortAlgorithm implements Algorithm {
    private Solution solution;

    @Override
    public Solution solve(Problem problem) {
        solution = problem.prepareSolution();
        assignTasks(problem.getInstance());
        solution.calculateDelay();
        return solution;
    }

    private void assignTasks(Instance instance) {
        List<Task> tasks = instance.getTasks()
                .stream()
                .sorted(Comparator.comparingInt(Task::getReadyTime).thenComparingInt(Task::getDueDate))
                .collect(Collectors.toList());
        for (Task task : tasks) {
            solution.getLowLoadedMachine().addTask(task);
        }
    }
}
