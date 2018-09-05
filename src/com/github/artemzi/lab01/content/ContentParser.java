package com.github.artemzi.lab01.content;

import com.github.artemzi.lab01.main.Occurrences;
import com.github.artemzi.lab01.utils.TypeConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ContentParser {
    private static final Logger LOGGER = Logger.getLogger(ContentParser.class.getName());

    private ContentParser(){}

    public static Set<String> parse(String[] words) {
        try {
            Content.getInstance().await();
        } catch (InterruptedException e) {
            LOGGER.info("Content.await was broken.");
        }

        executeSearchAction(words);

        try {
            ResultSet.getInstance().await();
        } catch (InterruptedException e) {
            LOGGER.info("ResultSet.await was broken.");
        }

        return ResultSet.getInstance().getData();
    }

    private static void executeSearchAction(String[] words) {
        ExecutorService pool = Executors.newFixedThreadPool(Occurrences.getMaxT());
        for (Byte[] data : Content.getInstance().getData()) {
            ResultSet.getInstance().add(1);
            pool.execute(() -> {
                String[] dataArray = new String(TypeConverter.toPrimitives(data), StandardCharsets.UTF_8)
                        .split("[.!?]");
                compare(dataArray, words);

                ResultSet.getInstance().done();
            });

        }
        pool.shutdown();
    }

    private static void compare(String[] data, String[] words) {
        for (String sentence : data) {
            for (String w : words) {
                if (sentence.toLowerCase().contains(w.toLowerCase())) {
                    ResultSet.getInstance().addData(sentence);
                }
            }
        }
    }
}
