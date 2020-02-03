package com.bubak.put.ptsz.generator.algorithm.genetic.modules;

import com.bubak.put.ptsz.core.model.GeneticSolution;
import com.bubak.put.ptsz.core.model.Task;

import java.util.List;
import java.util.Random;

public class CrossingModule {
    private final static int CROSSED_TASKS_PERCENT = 15;
    private final static int MIN_SWAPPED_TASKS_NUMBER = 3;
    private Random random = new Random();

    public GeneticSolution cross(GeneticSolution firstSolution, GeneticSolution secondSolution) {
        GeneticSolution solution = new GeneticSolution(firstSolution);
        int tasksNumberToSwap = calculateTasksNumberToSwap(solution.size() / CROSSED_TASKS_PERCENT);
        int startIndex = random.nextInt(solution.size() - tasksNumberToSwap);

        for (int i = startIndex; i < startIndex + tasksNumberToSwap; i++) {
            swap(solution.getTasks(), secondSolution.getTasks(), i);
        }
        return solution;
    }

    private int calculateTasksNumberToSwap(int size) {
        int tasksNumber = size > MIN_SWAPPED_TASKS_NUMBER ? size - MIN_SWAPPED_TASKS_NUMBER : size;
        return size > MIN_SWAPPED_TASKS_NUMBER ?
                random.nextInt(tasksNumber) + MIN_SWAPPED_TASKS_NUMBER : random.nextInt(size);
    }

    private void swap(List<Task> solution, List<Task> oldSolution, int index) {
        Task task = oldSolution.get(index);
        solution.remove(task);
        solution.add(index, task);
    }
}
