package com.bubak.put.ptsz.file.reader;

import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Machine;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SolutionReader {
    public Solution read(String path, Instance instance) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        int delay = parseSolutionDelay(lines);
        List<Machine> machines = parseMachines(lines, instance);
        return new Solution(machines, delay);
    }

    private int parseSolutionDelay(List<String> lines) {
        int delay = Integer.parseInt(lines.get(0));
        lines.remove(0);
        return delay;
    }

    private List<Machine> parseMachines(List<String> lines, Instance instance) {
        List<Machine> machines = new ArrayList<>();
        for (String line : lines) {
            Machine machine = new Machine();
            machine.setTasks(parseFileLineAsTasks(line, instance));
            machines.add(machine);
        }
        return machines;
    }

    private List<Task> parseFileLineAsTasks(String line, Instance instance) {
        String[] ids = line.split(" ");
        List<Task> tasks = new ArrayList<>();
        for (String id: ids) {
            Task task = new Task(instance.findTaskById(Integer.parseInt(id)).orElseThrow(IllegalStateException::new));
            tasks.add(task);
        }
        return tasks;
    }
}
