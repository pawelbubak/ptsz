package com.bubak.put.ptsz.generator.algorithm;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Task;

import java.util.List;

public class NaiveAlgorithm implements GenerationAlgorithm {
    private Solution solution;

    @Override
    public Solution solve(Problem problem) {
        solution = problem.prepareSolution();
        assignTasks(problem.getInstance());
        solution.calculateDelay();
        return solution;
    }

    private void assignTasks(Instance instance) {
        for (int i = 0; i < solution.getMachinesNumber(); i++) {
            assignTasksOnMachine(i, instance);
        }
    }

    private void assignTasksOnMachine(int machineNumber, Instance instance) {
        List<Task> tasks = prepareTasksForMachine(machineNumber, instance);
        solution.getMachine(machineNumber).setTasks(tasks);
    }

    private List<Task> prepareTasksForMachine(int machineNumber, Instance instance) {
        int startIndex = calculateIndexOfFirstTask(machineNumber, instance.getTasksQuantity());
        int endIndex = calculateIndexOfFirstTask(machineNumber + 1, instance.getTasksQuantity());
        return instance.getTasks().subList(startIndex, Math.min(endIndex, instance.getTasksQuantity()));
    }

    private int calculateIndexOfFirstTask(int machineNumber, int tasksNumber) {
        return (int)(Math.ceil((double) tasksNumber / (double) solution.getMachinesNumber()) * machineNumber);
    }
}
