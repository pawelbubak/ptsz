package com.bubak.put.ptsz.generator.algorithm.list;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Machine;
import com.bubak.put.ptsz.core.model.Task;

import java.util.List;

public class NaiveAlgorithm extends ListAlgorithm {
    protected void assignTasks(boolean generateList) {
        for (int i = 0; i < solution.getMachinesNumber(); i++) {
            assignTasksOnMachine(i, instance);
        }
        if (generateList) {
            this.tasks = instance.getTasks();
        }
    }

    private void assignTasksOnMachine(int machineNumber, Instance instance) {
        List<Task> tasks = prepareTasksForMachine(machineNumber, instance);
        Machine machine = solution.getMachine(machineNumber);
        tasks.forEach(machine::addTask);
    }

    private List<Task> prepareTasksForMachine(int machineNumber, Instance instance) {
        int startIndex = calculateIndexOfFirstTask(machineNumber, instance.getTasksQuantity());
        int endIndex = calculateIndexOfFirstTask(machineNumber + 1, instance.getTasksQuantity());
        return instance.getTasks().subList(startIndex, Math.min(endIndex, instance.getTasksQuantity()));
    }

    private int calculateIndexOfFirstTask(int machineNumber, int tasksNumber) {
        return (int) (Math.ceil((double) tasksNumber / (double) solution.getMachinesNumber()) * machineNumber);
    }
}
