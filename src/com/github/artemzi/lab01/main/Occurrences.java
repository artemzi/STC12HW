package com.github.artemzi.lab01.main;

import com.github.artemzi.lab01.content.ContentRequest;
import com.github.artemzi.lab01.content.ResultSet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Occurrences implements OccurrencesContact {
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());
    private static final String FILE_PATH = "data/lab01/";
    private static final int MAX_T = 20;
    private ExecutorService pool;

    public Occurrences() {
        pool = Executors.newFixedThreadPool(MAX_T);
    }

    @Override
    public void getOccurrences(String[] sources, String[] words, String res) throws IOException {
        executor(sources, words);

        HashSet<String> result = new HashSet<>(ResultSet.getInstance().getData());
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH + res, false));) {
            outputStream.writeObject(result);
        }
    }

    private void executor(String[] resources, String[] words) {
        for (String source : resources) {
            pool.execute(new ContentRequest(source, words));
        }
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
