package com.github.artemzi.lab01.main;

import com.github.artemzi.lab01.content.ContentRequest;
import com.github.artemzi.lab01.content.ResultProcessor;

import java.util.concurrent.*;

public class Occurrences implements OccurrencesContact {
    private static final int MAX_T = 20;
    private ExecutorService pool;

    public Occurrences() {
        pool = Executors.newFixedThreadPool(MAX_T);
    }

    @Override
    public void getOccurrences(String[] sources, String[] words, String res) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        for (String source : sources) {
            pool.execute(new ContentRequest(source, words, queue));
        }
        pool.execute(new ResultProcessor(queue, res));
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }
}
