package com.bubak.put.ptsz.generator.algorithm;

import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Task;

import java.util.List;

public interface Algorithm {
    Algorithm prepare(Problem problem);
    Solution run();
    List<Task> generateOrderedTasks();
}
