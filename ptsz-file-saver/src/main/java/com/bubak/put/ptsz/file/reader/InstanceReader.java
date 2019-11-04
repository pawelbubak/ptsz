package com.bubak.put.ptsz.file.reader;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstanceReader {
    public Instance read(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        int tasksQuantity = readInstanceSize(lines);
        List<Task> tasks = mapLineToTask(lines);
        return new Instance(tasks, tasksQuantity);
    }

    private int readInstanceSize(List<String> lines) {
        int tasksQuantity = Integer.parseInt(lines.get(0));
        lines.remove(0);
        return tasksQuantity;
    }

    private List<Task> mapLineToTask(List<String> lines) {
        ArrayList<Task> tasks = new ArrayList<>();
        int id = 1;
        for (String line : lines) {
            List<String> values = Arrays.asList(line.split(" "));
            Task task = new Task(id, Integer.valueOf(values.get(0)), Integer.valueOf(values.get(1)),
                    Integer.valueOf(values.get(2)));
            tasks.add(task);
            id++;
        }
        return tasks;
    }
}
