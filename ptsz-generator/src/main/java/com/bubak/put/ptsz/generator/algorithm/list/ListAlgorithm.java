package com.bubak.put.ptsz.generator.algorithm.list;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Task;
import com.bubak.put.ptsz.generator.algorithm.Algorithm;

import java.util.List;

public abstract class ListAlgorithm implements Algorithm {
    protected Solution solution;
    protected Instance instance;
    protected List<Task> tasks;

    @Override
    public Algorithm prepare(Problem problem) {
        solution = problem.prepareSolution();
        instance = problem.getInstance();
        return this;
    }

    @Override
    public Solution run() {
        assignTasks(false);
        solution.calculateDelay();
        return solution;
    }

    @Override
    public List<Task> generateOrderedTasks() {
        assignTasks(true);
        return tasks;
    }

    abstract protected void assignTasks(boolean generateList);
}
