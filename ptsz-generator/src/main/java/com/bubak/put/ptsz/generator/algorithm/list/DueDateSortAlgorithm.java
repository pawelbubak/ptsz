package com.bubak.put.ptsz.generator.algorithm.list;

import com.bubak.put.ptsz.core.model.Task;

import java.util.Comparator;
import java.util.stream.Collectors;

public class DueDateSortAlgorithm extends ListAlgorithm {
    protected void assignTasks(boolean generateList) {
        tasks = instance.getTasks()
                .stream()
                .sorted(Comparator.comparingInt(Task::getDueDate))
                .collect(Collectors.toList());
        for (Task task : tasks) {
            solution.getLowLoadedMachine().addTask(task);
        }
    }
}
