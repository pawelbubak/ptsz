package com.bubak.put.ptsz.generator.algorithm.genetic.modules;

import com.bubak.put.ptsz.core.model.GeneticSolution;
import com.bubak.put.ptsz.core.model.Task;

import java.util.List;
import java.util.Random;

public class MutationModule {
    private final static int MAX_SWAP_OPERATIONS = 3;
    private Random random = new Random();

    public GeneticSolution mutate(GeneticSolution solution) {
        GeneticSolution mutatedSolution = new GeneticSolution(solution);
        int swapOperations = random.nextInt(MAX_SWAP_OPERATIONS);
        for (int i = 0; i <= swapOperations; i++) {
            swapTasks(mutatedSolution.getTasks(), random.nextInt(mutatedSolution.size()),
                    random.nextInt(mutatedSolution.size()));
        }
        return mutatedSolution;
    }

    private void swapTasks(List<Task> tasks, int firstIndex, int secondIndex) {
        Task task = tasks.get(firstIndex);
        tasks.set(firstIndex, tasks.get(secondIndex));
        tasks.set(secondIndex, task);
    }
}
