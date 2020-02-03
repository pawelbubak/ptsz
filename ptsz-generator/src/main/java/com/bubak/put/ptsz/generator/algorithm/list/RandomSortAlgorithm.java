package com.bubak.put.ptsz.generator.algorithm.list;

import com.bubak.put.ptsz.core.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class RandomSortAlgorithm extends ListAlgorithm {
    protected void assignTasks(boolean generateList) {
        tasks = instance.getTasks()
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                        list -> {
                            Collections.shuffle(list);
                            return list;
                        }
                ));
        for (Task task : tasks) {
            solution.getLowLoadedMachine().addTask(task);
        }
    }
}
