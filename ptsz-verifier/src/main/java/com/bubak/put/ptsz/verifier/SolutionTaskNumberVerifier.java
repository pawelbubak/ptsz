package com.bubak.put.ptsz.verifier;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Statistic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolutionTaskNumberVerifier extends Verifier {
    @Override
    protected Statistic verifyInternal(Instance instance, Solution solution) {
        boolean correct = solution != null && solution.getTasksQuantity() == instance.getTasksQuantity();
        if (!correct) {
            log.info("The number of tasks is not valid:\n Expected number of tasks: {}\n The actual number of tasks: " +
                    "{}", instance != null ? instance.getTasksQuantity() : 0, solution != null ?
                    solution.getTasksQuantity() : 0);
        }
        return new Statistic(instance != null ? instance.getTasksQuantity() : 0, -1,
                solution != null ? solution.getDelay() : -1, correct);
    }
}
