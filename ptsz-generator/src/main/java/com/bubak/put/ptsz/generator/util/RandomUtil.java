package com.bubak.put.ptsz.generator.util;

import com.bubak.put.ptsz.generator.config.Config;

import java.util.Random;

public class RandomUtil {
    private Random random;

    public RandomUtil() {
        this.random = new Random();
    }

    public int randomTaskDuration() {
        return random.nextInt(Config.MAX_TASK_PROCESSING_TIME - Config.MIN_TASK_PROCESSING_TIME + 1) + Config.MIN_TASK_PROCESSING_TIME;
    }

    public int randomTaskStartTime(int maxStartTime, int avgProcessingTime) {
        return random.nextInt((int) Math.floor(Config.START_TIME_MODIFIER * maxStartTime - avgProcessingTime));
    }

    public int randomTaskEndTime(int taskStartTime, int taskProcessingTime) {
        return taskStartTime + taskProcessingTime + random.nextInt(Config.MAX_WINDOW_SIZE - Config.MIN_WINDOW_SIZE + 1) + Config.MIN_WINDOW_SIZE;
    }
}
