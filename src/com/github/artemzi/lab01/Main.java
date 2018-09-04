package com.github.artemzi.lab01;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Occurrences o = new Occurrences();
        o.getOccurrences(
                new String[]{
                    "https://en.wikinews.org/wiki/Judge_jails_%27monstrous%27_London_serial_killer_Stephen_Port",
                    "https://en.wikinews.org/wiki/Pop_culture_celebrated_at_Fan_Expo_Canada_2016_in_Toronto",
                    "https://en.wikinews.org/wiki/Wikinews_interviews_Rocky_De_La_Fuente,_U.S._Democratic_Party_presidential_candidate",
                    "file:data/lab01/test"
                },
                new String[]{
                    "one", "word"
                },
                "data"
        );

        try {
            Content.getInstance().await();
        } catch (InterruptedException e) {
            LOGGER.info("Counter.await was broken.");
        }

        for (Byte[] data : Content.getInstance().getData()) {
            System.out.println(new String(Content.getInstance().toPrimitives(data), StandardCharsets.UTF_8));
        }
        LOGGER.info("DONE");
    }
}
