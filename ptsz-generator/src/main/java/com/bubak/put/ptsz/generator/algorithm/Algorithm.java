package com.bubak.put.ptsz.generator.algorithm;

import com.bubak.put.ptsz.core.model.Problem;
import com.bubak.put.ptsz.core.model.Solution;

public interface Algorithm {
    Solution solve(Problem problem);
}
