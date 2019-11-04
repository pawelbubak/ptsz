package com.bubak.put.ptsz.verifier;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Statistic;

public abstract class Verifier {
    private Verifier nextVerifier;

    public Statistic verify(Instance instance, Solution solution) {
        Statistic statistic = verifyInternal(instance, solution);
        return nextVerifier != null && statistic.isCorrect() ? nextVerifier.verify(instance, solution) : statistic;
    }

    protected abstract Statistic verifyInternal(Instance instance, Solution solution);

    public Verifier registerNextVerifier(Verifier nextVerifier) {
        this.nextVerifier = nextVerifier;
        return this;
    }
}
