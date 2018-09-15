package com.github.artemzi.lab01.content;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ContentRequest implements Runnable {
    private String path;
    private static String[] words;
    private static BlockingQueue<String> queue;
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());

    public ContentRequest(String path, String[] wordsList, BlockingQueue<String> queueInstance) {
        this.path = path;
        words = wordsList;
        queue = queueInstance;
    }

    @Override
    public void run() {
        URL url = this.getUrl();
        if (url != null) {
            try (InputStream inputStream = url.openStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {

                StringBuilder buffer = new StringBuilder();
                int data = 0;
                while ((data = reader.read()) != -1) {
                    char rune = (char) data;
                    if (rune == '.' || rune == '!' || rune == '?') {
                        compareWithWordsAndSave(buffer.toString());
                        buffer = new StringBuilder(); // clean up
                    } else {
                        buffer.append(rune);
                    }
                }
            } catch (IOException | InterruptedException e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }

    private static void compareWithWordsAndSave(String sentence) throws InterruptedException {
        for (String w : words) {
            if (sentence.toLowerCase().contains(w.toLowerCase())) {
                queue.put(sentence);
                break; // if word found there is no need to search more
            }
        }
    }

    private URL getUrl() {
        URL url = null;
        try {
            url = new URL(this.path);
            LOGGER.info("URL created: " + url.toString());
        } catch (MalformedURLException e) {
            LOGGER.warning(e.getMessage());
        }
        return url;
    }
}
