package com.bubak.put.ptsz.file.saver;

import com.bubak.put.ptsz.core.Instance;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileSaver {
    public void saveInstance(Instance instance, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, String.valueOf(StandardCharsets.UTF_8));
        writer.println(instance.tasksQuantity());
        instance.getTasks().forEach(task -> writer.println(Formatter.formatTask(task)));
        writer.close();
    }
}
