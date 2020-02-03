package com.bubak.put.ptsz.generator.algorithm.genetic;

import com.bubak.put.ptsz.core.model.*;
import com.bubak.put.ptsz.generator.algorithm.Algorithm;
import com.bubak.put.ptsz.generator.algorithm.AlgorithmFactory;
import com.bubak.put.ptsz.generator.algorithm.AlgorithmType;
import com.bubak.put.ptsz.generator.algorithm.genetic.modules.CrossingModule;
import com.bubak.put.ptsz.generator.algorithm.genetic.modules.MutationModule;
import com.bubak.put.ptsz.generator.algorithm.genetic.modules.SelectionModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm implements Algorithm {
    private final static int POPULATION = 25;
    private final static int GENERATIONS = 7500;
    private final static int CROSS_RATIO = 60;
    private final static int MUTATION_RATIO = 60;
    private CrossingModule crossingModule = new CrossingModule();
    private MutationModule mutationModule = new MutationModule();
    private SelectionModule selectionModule = new SelectionModule();
    private Problem problem;
    private List<GeneticSolution> solutions = new ArrayList<>();
    private Random random = new Random();

    @Override
    public GeneticAlgorithm prepare(Problem problem) {
        this.problem = problem;
        prepareFirstPopulation();
        return this;
    }

    @Override
    public Solution run() {
        for (int i = 0; i < GENERATIONS; i++) {
            prepareNextPopulation();
        }
        return getSolution();
    }

    @Override
    public List<Task> generateOrderedTasks() {
        for (int i = 0; i < GENERATIONS; i++) {
            prepareNextPopulation();
        }
        return getSolutionAsTaskList();
    }

    private void prepareFirstPopulation() {
//        generateSolutionsByAllAlgorithms();
        Algorithm algorithm = AlgorithmFactory.createAlgorithm(AlgorithmType.SIMPLE);
        while (solutions.size() < POPULATION / 2) {
            List<Task> tasks = algorithm.prepare(problem).generateOrderedTasks();
            solutions.add(new GeneticSolution(tasks));
        }
        algorithm = AlgorithmFactory.createAlgorithm(AlgorithmType.ADVANCED);
        while (solutions.size() < POPULATION) {
            List<Task> tasks = algorithm.prepare(problem).generateOrderedTasks();
            solutions.add(new GeneticSolution(tasks));
        }
    }

    private void prepareNextPopulation() {
        List<GeneticSolution> newPopulation = new ArrayList<>(solutions);
        for (GeneticSolution solution : solutions) {
            if (random.nextInt(100) < MUTATION_RATIO) {
                newPopulation.add(mutationModule.mutate(solution));
            }
            if (random.nextInt(100) < CROSS_RATIO) {
                newPopulation.add(crossingModule.cross(solution, getRandomSolution()));
            }
        }
        solutions = newPopulation;
        calculateDelays();
        solutions = selectionModule.select(solutions, POPULATION);
    }

    private void generateSolutionsByAllAlgorithms() {
        for (AlgorithmType algorithmType : AlgorithmType.values()) {
            if (!algorithmType.equals(AlgorithmType.GENETIC)) {
                Algorithm algorithm = AlgorithmFactory.createAlgorithm(algorithmType);
                List<Task> tasks = algorithm.prepare(problem).generateOrderedTasks();
                solutions.add(new GeneticSolution(tasks));
            }
        }
    }

    private Solution getSolution() {
        Solution solution = prepareSolution();
        List<Task> tasks = getSolutionAsTaskList();
        for (Task task : tasks) {
            solution.getLowLoadedMachine().addTask(task);
        }
        solution.calculateDelay();
        return solution;
    }

    private List<Task> getSolutionAsTaskList() {
        return solutions.stream()
                .min(Comparator.comparingInt(GeneticSolution::getDelay))
                .map(GeneticSolution::getTasks).orElse(new ArrayList<>());
    }

    private GeneticSolution getRandomSolution() {
        return solutions.get(random.nextInt(solutions.size()));
    }

    private void calculateDelays() {
        solutions.stream().parallel().forEach(GeneticSolution::calculateDelay);
    }

    private Solution prepareSolution() {
        Solution solution = new Solution();
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            machines.add(new Machine());
        }
        solution.setMachines(machines);
        return solution;
    }
}
