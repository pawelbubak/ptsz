package com.bubak.put.ptsz.generator.algorithm.list;

import com.bubak.put.ptsz.core.model.Machine;
import com.bubak.put.ptsz.core.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleSortAlgorithm extends ListAlgorithm {
    protected void assignTasks(boolean generateList) {
        List<Task> tasks = new ArrayList<>(instance.getTasks());
        this.tasks = new ArrayList<>();
        while (!tasks.isEmpty()) {
            Machine machine = solution.getLowLoadedMachine();
            Task task = tasks.stream()
                    .min(Comparator.comparingDouble(value -> {
                        Task val = (Task) value;
                        return Math.max(val.getReadyTime() - machine.getLastEndTime(), 0);
                    }).thenComparingInt(value -> {
                        Task val = (Task) value;
                        return val.getProcessingTime();
                    })).orElse(null);
            machine.addTask(task);
            tasks.remove(task);
            if (generateList) {
                this.tasks.add(task);
            }
        }
    }
}
