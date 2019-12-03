package com.bubak.put.ptsz.core.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    private List<Machine> machines = new ArrayList<>();
    private int delay;
    private double calculationTime;

    public void calculateDelay() {
        delay = getTasksSchedulingDelay();
    }

    public Machine getMachine(int machine) {
        return machines.get(machine);
    }

    public int getMachinesNumber() {
        return machines != null ? machines.size() : 0;
    }

    public int getTasksQuantity() {
        int tasksQuantity = 0;
        if (machines != null) {
            for (Machine machine : machines) {
                tasksQuantity += machine != null ? machine.getTasksQuantity() : 0;
            }
        }
        return tasksQuantity;
    }

    public int getTasksSchedulingDelay() {
        int tasksSchedulingDelay = 0;
        if (machines != null) {
            for (Machine machine : machines) {
                tasksSchedulingDelay += machine.getTasksSchedulingDelay();
            }
        }
        return tasksSchedulingDelay;
    }

    public Machine getLowLoadedMachine() {
        return machines.stream().min(Comparator.comparingLong(Machine::getLastEndTime)).orElse(null);
    }
}
