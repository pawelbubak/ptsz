package com.bubak.put.ptsz.file.saver;


import com.bubak.put.ptsz.core.model.Task;

public class Formatter {
    public static String formatTask(Task task) {
        return String.format("%d %d %d", task.getProcessingTime(), task.getReadyTime(), task.getDueDate());
    }
}
