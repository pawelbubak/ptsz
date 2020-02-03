package com.bubak.put.ptsz.verifier;

import com.bubak.put.ptsz.core.config.FileConfig;
import com.bubak.put.ptsz.core.model.*;
import com.bubak.put.ptsz.file.reader.InstanceReader;
import com.bubak.put.ptsz.file.reader.SolutionReader;
import com.bubak.put.ptsz.file.saver.FileSaver;
import com.bubak.put.ptsz.file.utils.FileUtils;
import com.bubak.put.ptsz.generator.algorithm.list.NaiveAlgorithm;
import com.bubak.put.ptsz.generator.solution.SolutionGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class VerifierApp {
    private static String INDEX = "132197";

    public static void main(String[] args) throws IOException {
        VerifierApp verifier = new VerifierApp();
        Statistics statistics = new Statistics(INDEX);
        for (int i = 1; i < 11; i++) {
            statistics.addStatistic(verifier.verify(statistics.getOwner(), i * 50));
        }
        FileSaver saver = new FileSaver();
        saver.saveStatistics(statistics);
    }

    private Statistic verify(String index, int instanceSize) {
        Instance instance = loadInstance(index, instanceSize);
        Solution solution = loadSolution(index, instanceSize, instance);
        Verifier verifier = prepareVerifiersChain();
        Statistic statistic = verifier.verify(instance, solution);
        if (statistic.isCorrect()) {
            log.info("{} OK", prepareSolutionFileName(index, instanceSize));
        } else {
            log.info("{} NOT OK", prepareSolutionFileName(index, instanceSize));
        }
        return statistic;
    }

    private Instance loadInstance(String index, int instanceSize) {
        String path = preparePathToInstanceFile(index, instanceSize);
        InstanceReader reader = new InstanceReader();
        try {
            return InstanceReader.read(path);
        } catch (IOException e) {
            String message = String.format("Unable to open instance file [%s].", path);
            log.error(message, e);
            throw new IllegalStateException(message, e);
        }
    }

    private Solution loadSolution(String index, int instanceSize, Instance instance) {
        String path = preparePathToSolutionFile(index, instanceSize);
        SolutionReader reader = new SolutionReader();
        try {
            return reader.read(path, instance);
        } catch (IOException e) {
            String message = String.format("Unable to open solution file [%s].", path);
            log.error(message, e);
            try {
                createSolutionFile(instance, path);
            } catch (IOException e1) {
                String msg = String.format("Unable to create solution file [%s].", path);
                log.error(msg, e1);
                throw new IllegalStateException(msg, e1);
            }
        }
        return null;
    }

    private Verifier prepareVerifiersChain() {
        return new InstanceTaskNumberVerifier().registerNextVerifier(new SolutionTaskNumberVerifier().registerNextVerifier(new SolutionVerifier()));
    }

    private String preparePathToInstanceFile(String index, int instanceSize) {
        return FileConfig.INSTANCES_FOLDER + FileUtils.prepareFileName(FileConfig.INSTANCE_FILE_PREFIX, index,
                instanceSize);
    }

    private String preparePathToSolutionFile(String index, int instanceSize) {
        return FileConfig.SOLUTIONS_FOLDER + prepareSolutionFileName(index, instanceSize);
    }

    private void createSolutionFile(Instance instance, String path) throws IOException {
        Problem problem = new Problem(instance, 4);

        SolutionGenerator generator = new SolutionGenerator();
        generator.setAlgorithm(new NaiveAlgorithm());
//        generator.setAlgorithm(new SimpleSortAlgorithm());
        Solution solution = generator.generateSolution(problem);

        FileSaver fileSaver = new FileSaver();
        fileSaver.saveSolution(solution, path);
    }

    private String prepareSolutionFileName(String index, int instanceSize) {
        return FileUtils.prepareFileName(FileConfig.SOLUTION_FILE_PREFIX, index, instanceSize);
    }
}
