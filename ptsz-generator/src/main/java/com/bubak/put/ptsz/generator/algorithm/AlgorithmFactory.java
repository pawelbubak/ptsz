package com.bubak.put.ptsz.generator.algorithm;

import com.bubak.put.ptsz.generator.algorithm.genetic.GeneticAlgorithm;
import com.bubak.put.ptsz.generator.algorithm.list.*;

public class AlgorithmFactory {
    public static Algorithm createAlgorithm(AlgorithmType algorithmType) {
        Algorithm algorithm;
        switch (algorithmType) {
            case ADVANCED:
                algorithm = new AdvancedSortAlgorithm();
                break;
            case DUE_DATE:
                algorithm = new DueDateSortAlgorithm();
                break;
            case GENETIC:
                algorithm = new GeneticAlgorithm();
                break;
            case RANDOM:
                algorithm = new RandomSortAlgorithm();
                break;
            case READY_TIME:
                algorithm = new ReadyTimeSortAlgorithm();
                break;
            case RD:
                algorithm = new ReadyTimeAndDueDateAlgorithm();
                break;
            case SIMPLE:
                algorithm = new SimpleSortAlgorithm();
                break;
            default:
                algorithm = new NaiveAlgorithm();
        }
        return algorithm;
    }
}
