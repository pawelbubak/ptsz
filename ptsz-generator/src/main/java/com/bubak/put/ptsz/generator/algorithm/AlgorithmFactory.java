package com.bubak.put.ptsz.generator.algorithm;

import com.bubak.put.ptsz.generator.algorithm.algorithms.*;

public class AlgorithmFactory {
    public static Algorithm createAlgorithm(AlgorithmType algorithmType) {
        Algorithm algorithm;
        switch (algorithmType) {
            case A_SORT:
                algorithm = new AdvancedSortAlgorithm();
                break;
            case DD_SORT:
                algorithm = new DueDateSortAlgorithm();
                break;
            case RT_SORT:
                algorithm = new ReadyTimeSortAlgorithm();
                break;
            case S_SORT:
                algorithm = new SimpleSortAlgorithm();
                break;
            default:
                algorithm = new NaiveAlgorithm();
        }
        return algorithm;
    }
}
