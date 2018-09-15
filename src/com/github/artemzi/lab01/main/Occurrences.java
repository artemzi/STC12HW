package com.github.artemzi.lab01.main;

import com.github.artemzi.lab01.content.ContentRequest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Occurrences implements OccurrencesContact {
    private static final String FILE_PATH = "data/lab01/";
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());
    private static final int MAX_T = 20;
    private ExecutorService pool;

    public Occurrences() {
        pool = Executors.newFixedThreadPool(MAX_T);
    }

    @Override
    public void getOccurrences(String[] sources, String[] words, String res) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Thread fileWriter = new Thread(() -> {
            try (PrintWriter writer = new PrintWriter(new File(FILE_PATH + res));) {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (IOException | InterruptedException e) {
                LOGGER.warning(e.getMessage());
            }
        });
        fileWriter.start();
        for (String source : sources) {
            pool.execute(new ContentRequest(source, words, queue));
        }
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        fileWriter.interrupt(); // rescue fileWriter from infinity loop
    }
}
