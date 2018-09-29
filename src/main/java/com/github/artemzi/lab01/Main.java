package com.github.artemzi.lab01;

import com.github.artemzi.lab01.main.Occurrences;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final long START_TIME = System.nanoTime();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static String[] getFIleNames() {
        File folder = new File("data/lab01/testSet");
        File[] listOfFiles = folder.listFiles();
        List<String> results = new ArrayList<>();

        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                results.add("file:data/lab01/testSet/" + listOfFile.getName());
            }
        }
        LOGGER.info("file paths was created");
        return results.toArray(new String[0]);
    }

    public static void main(String[] args) {
        LOGGER.info("App started.");
        Occurrences o = new Occurrences();
//        Scanner in = new Scanner(System.in);
//        in.nextLine(); // wait for profiler

        try {
            o.getOccurrences(
                    getFIleNames(),
                    new String[]{
                        "tortor", "risus", "Rlbfeaj", "Veykpth", "asioynp", "quubpqhpd", "Lorem", "ipsum"
                    },
                    "RESULT"
            );
        } catch (InterruptedException e) {
            LOGGER.error("[getOccurrences] was terminated.");
        }

        long timeElapsed = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - START_TIME) + 1;
        LOGGER.info("[done] Total execution time: " + timeElapsed + " seconds");
    }
}
