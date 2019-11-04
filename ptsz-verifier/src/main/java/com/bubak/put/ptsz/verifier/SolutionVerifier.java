package com.bubak.put.ptsz.verifier;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Statistic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolutionVerifier extends Verifier {
    @Override
    protected Statistic verifyInternal(Instance instance, Solution solution) {
        Statistic statistic = new Statistic();
        if (solution != null) {
            statistic.setInstanceSize(solution.getTasksQuantity());
            statistic.setExpectedResult(solution.getDelay());
            statistic.setResult(solution.getTasksSchedulingDelay());
            statistic.setCorrect(solution.getDelay() == solution.getTasksSchedulingDelay());
            if (!statistic.isCorrect()) {
                log.info("Delay of tasks is not valid!");
            }
        }
        return statistic;
    }
}
