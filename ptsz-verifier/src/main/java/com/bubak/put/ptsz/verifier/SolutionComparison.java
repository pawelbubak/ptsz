package com.bubak.put.ptsz.verifier;

import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.file.utils.FileUtils;
import com.bubak.put.ptsz.generator.algorithm.AlgorithmType;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SolutionComparison {
    private final static AlgorithmType FIRST_ALGORITHM_TYPE = AlgorithmType.NAIVE;
    private final static AlgorithmType SECOND_ALGORITHM_TYPE = AlgorithmType.A_SORT;
    private final static boolean SAVE_STATISTICS = true;

    public static void main(String[] args) {
//        List<String> studentsIndexes = FileUtils.getStudentsIndexes();
        List<String> studentsIndexes = Arrays.asList("132225", "132214", "132219", "132195", "125342", "132209",
                "132207", "132221", "127173", "132349", "132348", "132197", "132319", "132215", "127329", "132280", "126151", "132192");
        for (String studentsIndex : studentsIndexes) {
            generateStudentSolutions(studentsIndex);
        }
    }

    private static void generateStudentSolutions(String studentsIndex) {
        log.info("==================================== {} ====================================", studentsIndex);
        for (int size = 50; size <= 500; size += 50) {
            Solution firstSolution = null, secondSolution = null;
            String fileName = FileUtils.prepareFileName("in", studentsIndex, size);
            String path = "instances/" + fileName;
            double time = 0;
            for (int i = 0; i < 500; i++) {
                firstSolution = SolutionGenerator.generateStudentSolution(path, FIRST_ALGORITHM_TYPE);
                secondSolution = SolutionGenerator.generateStudentSolution(path, SECOND_ALGORITHM_TYPE);
                time += secondSolution.getCalculationTime();
            }
            secondSolution.setCalculationTime(time / 500);
            fileName = FileUtils.prepareFileName("st", studentsIndex, size);
            path = "statistics/" + fileName;
            saveStatisticsIfNecessary(firstSolution, secondSolution, path);
        }
    }

    private static void saveStatisticsIfNecessary(Solution firstSolution, Solution secondSolution, String path) {
        if (SAVE_STATISTICS && firstSolution != null && secondSolution != null) {
            long firstAlgorithmDelay = firstSolution.getTasksSchedulingDelay();
            long secondAlgorithmDelay = secondSolution.getTasksSchedulingDelay();
            System.out.println(firstSolution.getTasksQuantity() + " | " +
                    secondSolution.getCalculationTime() + " | " +
                    secondAlgorithmDelay + " | " +
                    firstAlgorithmDelay + " | " +
                    (firstAlgorithmDelay - secondAlgorithmDelay) * 100 / secondAlgorithmDelay);
        }
    }
}
