package com.github.artemzi.lab01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Occurrences implements OccurrencesContact {
    private static final int MAX_T = 20;
    private ExecutorService pool;

    Occurrences() {
        pool = Executors.newFixedThreadPool(MAX_T);
    }

    @Override
    public void getOccurrences(String[] sources, String[] words, String res) {
        Content.getInstance().add(sources.length); // Initialize WaitGroup with values
        for (String source : sources) {
            pool.execute(new ContentRequest(source));
        }
        pool.shutdown();
    }
}
