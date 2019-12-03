package com.bubak.put.ptsz.generator.solution;

import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.generator.algorithm.Algorithm;
import com.bubak.put.ptsz.generator.algorithm.algorithms.NaiveAlgorithm;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolutionGenerator {
    private Algorithm algorithm;

    public SolutionGenerator() {
        this.algorithm = new NaiveAlgorithm();
    }

    public void setAlgorithm(@NotNull Algorithm algorithm) {
        this.algorithm = algorithm;
        log.info("Task scheduling algorithm has been set.");
    }

    public Solution generateSolution(Problem problem) {
        log.info("Starting solution generation..");
        Solution solution = algorithm.solve(problem);
        log.info("Solution generation finished");
        return solution;
    }
}
