package com.bubak.put.ptsz.generator;

import com.bubak.put.ptsz.core.Instance;
import com.bubak.put.ptsz.core.Task;
import com.bubak.put.ptsz.file.saver.FileSaver;

import java.io.IOException;
import java.util.Comparator;

public class Generator {
    public static void main(String[] args) {
        InstanceGenerator instanceGenerator = new InstanceGenerator();
        FileSaver fileSaver = new FileSaver();

        for (int i = 1; i <= 10; i++) {
            int tasksQuantity = 50 * i;
            Instance instance = instanceGenerator.generate(tasksQuantity);
            instance.getTasks().sort(Comparator.comparingInt(Task::getReadyTime));
            try {
                fileSaver.saveInstance(instance, String.format("instance_%d.txt", tasksQuantity));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
