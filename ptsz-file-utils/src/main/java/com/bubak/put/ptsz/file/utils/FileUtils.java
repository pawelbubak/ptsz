package com.bubak.put.ptsz.file.utils;

import com.bubak.put.ptsz.core.config.FileConfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
    public static String prepareFileName(String prefix, String index, int instanceSize) {
        return prepareFileName(prefix, index, instanceSize, FileConfig.DEFAULT_EXTENSION);
    }

    public static String prepareFileName(String prefix, String index, int instanceSize, String extension) {
        return String.format(FileConfig.FILE_NAME_PATTERN, prefix, index, instanceSize, extension);
    }

    public static String getDirectoryFromPath(String path) {
        int endIndex = path.lastIndexOf('/');
        return endIndex > 0 ? path.substring(0, endIndex) : "";
    }

    public static List<String> getStudentsIndexes() {
        List<String> students;
        try (Stream<Path> walk = Files.walk(Paths.get("instances"))) {
            students = walk.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .map(fileName -> fileName.substring(2, fileName.indexOf("_")))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception ignored) {
            students = new ArrayList<>();
        }
        return students;
    }
}
