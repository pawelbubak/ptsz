package com.bubak.put.ptsz.generator.algorithm.genetic.modules;

import com.bubak.put.ptsz.core.model.GeneticSolution;

import java.util.Comparator;
import java.util.List;

public class SelectionModule {
    public List<GeneticSolution> select(List<GeneticSolution> solutions, int quantity) {
        solutions.sort(Comparator.comparingInt(GeneticSolution::getDelay));
        return solutions.subList(0, quantity);
    }
}
