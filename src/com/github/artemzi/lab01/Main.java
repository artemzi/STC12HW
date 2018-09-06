package com.github.artemzi.lab01;

import com.github.artemzi.lab01.main.Occurrences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main {
    private static final long START_TIME = System.nanoTime();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Occurrences o = new Occurrences();
        try {
            o.getOccurrences(
                    new String[]{
                        "https://en.wikinews.org/wiki/Judge_jails_%27monstrous%27_London_serial_killer_Stephen_Port",
                        "https://en.wikinews.org/wiki/Pop_culture_celebrated_at_Fan_Expo_Canada_2016_in_Toronto",
                        "https://en.wikinews.org/wiki/Wikinews_interviews_Rocky_De_La_Fuente,_U.S._Democratic_Party_presidential_candidate",
                        "file:data/lab01/test", // 1.1K
                        "file:data/lab01/test2", // 1.1M
                        "file:data/lab01/bible.txt", // 24M
                        "file:data/lab01/world192.txt", // 2.4M
                        "file:data/lab01/world", // 2.4G
                    },
                    new String[]{
                        "chunk", "became"
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
