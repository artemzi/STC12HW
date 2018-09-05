package com.github.artemzi.lab01.main;

import com.github.artemzi.lab01.content.Content;
import com.github.artemzi.lab01.content.ContentParser;
import com.github.artemzi.lab01.content.ContentRequest;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Occurrences implements OccurrencesContact {
    private static final int MAX_T = 20;
    private ExecutorService pool;

    public Occurrences() {
        pool = Executors.newFixedThreadPool(MAX_T);
    }

    @Override
    public void getOccurrences(String[] sources, String[] words, String res) {
        executor(sources);

        HashSet<String> result = new HashSet<>(ContentParser.parse(words));
        System.out.println(result);
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
