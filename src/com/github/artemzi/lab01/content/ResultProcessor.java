package com.github.artemzi.lab01.content;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ResultProcessor implements Runnable {
    private final BlockingQueue<String> queue;
    private static String FILE_PATH;
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());

    public ResultProcessor(BlockingQueue<String> queueInstance, String fileToWriteResult) {
        this.queue = queueInstance;
        FILE_PATH = "data/lab01/" + fileToWriteResult;
    }

    @Override
    public void run() {
        String foundedSentence = "";
        try (PrintWriter writer = new PrintWriter(new File(FILE_PATH));) {
            while ((foundedSentence = queue.poll()) != null) {
                writer.println(foundedSentence);
            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
