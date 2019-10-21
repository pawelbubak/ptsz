package com.bubak.put.ptsz.generator.util;

import com.bubak.put.ptsz.core.Task;
import com.bubak.put.ptsz.generator.config.Config;

import java.util.List;

public class Util {
    public static int calculateAverageProcessingTime(List<Task> tasks) {
        double avgProcTime = tasks.stream().mapToDouble(Task::getProcessingTime).average().orElse(Double.NaN);
        return (int) Math.floor(avgProcTime);
    }

    public static int calculateMaxTaskStartTime(int averageDuration, int tasksQuantity) {
        return averageDuration * tasksQuantity / Config.PROCESSORS_QUANTITY;
    }
}
