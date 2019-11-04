package com.bubak.put.ptsz.file.util;

import com.bubak.put.ptsz.core.config.FileConfig;

public class Util {
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
}
