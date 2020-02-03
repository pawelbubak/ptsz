package com.bubak.put.ptsz.file.saver;

import com.bubak.put.ptsz.core.config.FileConfig;
import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.core.model.Solution;
import com.bubak.put.ptsz.core.model.Statistics;
import com.bubak.put.ptsz.file.utils.FileUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileSaver {
    public void saveInstance(Instance instance, String path) throws IOException {
        prepareDirectory(path);
        PrintWriter writer = new PrintWriter(path, String.valueOf(StandardCharsets.UTF_8));
        writer.println(instance.getTasksQuantity());
        instance.getTasks().forEach(task -> writer.println(Formatter.formatTask(task)));
        writer.close();
    }

    public void saveSolution(Solution solution, String path) throws IOException {
        prepareDirectory(path);
        PrintWriter writer = new PrintWriter(path, String.valueOf(StandardCharsets.UTF_8));
        writer.println(solution.getDelay());
        solution.getMachines().forEach(machine -> writer.println(machine.getTasks().stream().map(task -> String.valueOf(task.getId())).collect(Collectors.joining(" "))));
        writer.close();
    }

    public void saveStatistics(Statistics statistics) throws IOException {
        String path = "statistics/st_" + statistics.getOwner() + "." + FileConfig.DEFAULT_EXTENSION;
        prepareDirectory(path);
        PrintWriter writer = new PrintWriter(path, String.valueOf(StandardCharsets.UTF_8));
        statistics.getStatistics().forEach(statistic -> writer.println(statistic.getInstanceSize() + " " + statistic.getExpectedResult() + " " + statistic.getResult() + " " + (statistic.isCorrect() ? "OK" : "NOT OK")));
        writer.close();
    }

    private void prepareDirectory(String path) throws IOException {
        String directoryName = FileUtils.getDirectoryFromPath(path);
        if (Files.notExists(Paths.get(directoryName))) {
            Files.createDirectory(Paths.get(directoryName));
        }
    }
}
