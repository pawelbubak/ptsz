package com.bubak.put.ptsz.verifier;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.file.reader.InstanceReader;
import com.bubak.put.ptsz.file.saver.FileSaver;
import com.bubak.put.ptsz.file.utils.FileUtils;
import com.bubak.put.ptsz.generator.algorithm.Algorithm;
import com.bubak.put.ptsz.generator.algorithm.AlgorithmFactory;
import com.bubak.put.ptsz.generator.algorithm.AlgorithmType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class SolutionGenerator {
    private final static AlgorithmType ALGORITHM_TYPE = AlgorithmType.A_SORT;
    private final static boolean SAVE_SOLUTION = true;
    private final static int MACHINES_NUMBER = 4;

    public static void main(String[] args) {
//        List<String> studentsIndexes = FileUtils.getStudentsIndexes();
        List<String> studentsIndexes = Collections.singletonList("132197");
        for (String studentsIndex : studentsIndexes) {
            generateStudentSolutions(studentsIndex);
        }
    }

    private static void generateStudentSolutions(String studentsIndex) {
        log.info("==================================== {} ====================================", studentsIndex);
        for (int size = 50; size <= 500; size += 50) {
            String fileName = FileUtils.prepareFileName("in", studentsIndex, size);
            String path = "instances/" + fileName;
            Solution solution = generateStudentSolution(path, ALGORITHM_TYPE);
            fileName = FileUtils.prepareFileName("out", studentsIndex, size);
            path = "solutions/" + fileName;
            saveSolutionIfNecessary(solution, path);
        }
    }

    public static Solution generateStudentSolution(String pathToInstance, AlgorithmType algorithmType) {
        Solution solution = null;
        try {
            Instance instance = InstanceReader.read(pathToInstance);
            Algorithm algorithm = AlgorithmFactory.createAlgorithm(algorithmType);
            StopWatch watch = new StopWatch();
            watch.start();
            solution = algorithm.solve(new Problem(instance, MACHINES_NUMBER));
            watch.stop();
            solution.setCalculationTime(watch.getNanoTime() / 1000.0);
            log.debug("Instance[{}]: k={}, t={}", instance.getTasksQuantity(), solution.getTasksSchedulingDelay(),
                    solution.getCalculationTime());
        } catch (IOException error) {
            log.error("Instance[" + pathToInstance + "] was not loaded!", error);
        }
        return solution;
    }

    private static void saveSolutionIfNecessary(Solution solution, String pathToSolution) {
        if (SAVE_SOLUTION && solution != null) {
            FileSaver fileSaver = new FileSaver();
            try {
                fileSaver.saveSolution(solution, pathToSolution);
            } catch (IOException error) {
                log.error("Solution[" + pathToSolution + "] could not be saved!", error);
            }
        }
    }
}
