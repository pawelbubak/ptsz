package com.bubak.put.ptsz.generator.algorithm;

import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;

public interface GenerationAlgorithm {
    Solution solve(Problem problem);
}
