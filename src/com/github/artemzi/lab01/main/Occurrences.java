package com.github.artemzi.lab01.main;

import com.github.artemzi.lab01.content.ContentRequest;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;

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
                    writer.println(queue.take()); // take() will wait for content in queue if it not already exists
                }
            } catch (IOException | InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        });
        fileWriter.start();
        LOGGER.info("fileWriter was started");
        for (String source : sources) {
            pool.execute(new ContentRequest(source, words, queue));
            LOGGER.info("Job executed for source: " + source);
        }
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        fileWriter.interrupt(); // rescue fileWriter from infinity loop
    }
}
