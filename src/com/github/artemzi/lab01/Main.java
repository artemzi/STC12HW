package com.github.artemzi.lab01;

import com.github.artemzi.lab01.main.Occurrences;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main {
    private static final long START_TIME = System.nanoTime();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static String[] getFIleNames() {
        File folder = new File("data/lab01/testSet");
        File[] listOfFiles = folder.listFiles();
        List<String> results = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                results.add("file:data/lab01/testSet/" + listOfFiles[i].getName());
            }
        }
        return results.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Occurrences o = new Occurrences();
        Scanner in = new Scanner(System.in);
        in.nextLine();

        try {
            o.getOccurrences(
                    getFIleNames(),
                    new String[]{
                        "tortor", "risus"
                    },
                    "RESULT"
            );
        } catch (IOException | InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }

        long timeElapsed = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - START_TIME) + 1;
        LOGGER.info("[done] Total execution time: " + timeElapsed + " seconds");
    }
}
