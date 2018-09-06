package com.github.artemzi.lab01.main;

import com.github.artemzi.lab01.content.Content;
import com.github.artemzi.lab01.content.ContentParser;
import com.github.artemzi.lab01.content.ContentRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Occurrences implements OccurrencesContact {
    private static final String FILE_PATH = "data/lab01/";
    private static final int MAX_T = 20;
    private ExecutorService pool;

    public Occurrences() {
        pool = Executors.newFixedThreadPool(MAX_T);
    }

    @Override
    public void getOccurrences(String[] sources, String[] words, String res) throws IOException {
        executor(sources);

        HashSet<String> result = new HashSet<>(ContentParser.parse(words));
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH + res, false));) {
            outputStream.writeObject(result);
        }
    }

    private void executor(String[] resources) {
        Content.getInstance().add(resources.length); // Initialize WaitGroup with values
        for (String source : resources) {
            pool.execute(new ContentRequest(source));
        }
        pool.shutdown();
    }

    public static int getMaxT() {
        return MAX_T;
    }
}
