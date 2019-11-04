package com.bubak.put.ptsz.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private Instance instance;
    private int machinesNumber;

    public Solution prepareSolution() {
        Solution solution = new Solution();
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < machinesNumber; i++) {
            machines.add(new Machine());
        }
        solution.setMachines(machines);
        return solution;
    }
}
