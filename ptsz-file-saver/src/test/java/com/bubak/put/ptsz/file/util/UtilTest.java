package com.bubak.put.ptsz.file.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {
    private static String expectedFileName = "in_132197_50.txt";

    @Test
    void shouldPrepareCorrectFileNameWithDefaultExtension() {
        String fileName = Util.prepareFileName("in", "132197", 50);
        assertEquals(expectedFileName, fileName);
    }

    @Test
    void shouldPrepareCorrectFileName() {
        String fileName = Util.prepareFileName("in", "132197", 50, "txt");
        assertEquals(expectedFileName, fileName);
    }

    @Test
    void shouldReturnDirectoryName() {
        String expectedDirectory = "includes";
        String directory = Util.getDirectoryFromPath(expectedDirectory + "/" + expectedFileName);
        assertEquals(expectedDirectory, directory);
    }

    @Test
    void shouldReturnEmptyString() {
        String expectedDirectory = "";
        String directory = Util.getDirectoryFromPath(expectedDirectory + expectedFileName);
        assertEquals(expectedDirectory, directory);
    }
}
