package com.bubak.put.ptsz.generator;

import com.bubak.put.ptsz.core.config.FileConfig;
import com.bubak.put.ptsz.core.model.Instance;
import com.bubak.put.ptsz.file.saver.FileSaver;
import com.bubak.put.ptsz.file.util.Util;
import com.bubak.put.ptsz.generator.instance.InstanceGenerator;

import java.io.IOException;

public class Generator {
    public static void main(String[] args) {
        InstanceGenerator instanceGenerator = new InstanceGenerator();
        FileSaver fileSaver = new FileSaver();

        for (int i = 1; i <= 10; i++) {
            int tasksNumber = 50 * i;
            Instance instance = instanceGenerator.generate(tasksNumber);
            try {
                fileSaver.saveInstance(instance, prepareFilePath(tasksNumber));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static String prepareFilePath(int instanceSize) {
        return FileConfig.INSTANCES_FOLDER + Util.prepareFileName(FileConfig.INSTANCE_FILE_PREFIX, "132197",
                instanceSize);
    }
}
