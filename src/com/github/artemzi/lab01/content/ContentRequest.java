package com.github.artemzi.lab01.content;

import com.github.artemzi.lab01.exceptions.CannotAddContentException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class ContentRequest implements Runnable {
    private String path;
    private static String[] words;
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());

    public ContentRequest(String path, String[] wordsList) {
        this.path = path;
        words = wordsList;
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
            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }

    private static void compareWithWordsAndSave(String sentence) {
        for (String w : words) {
            if (sentence.toLowerCase().contains(w.toLowerCase())) {
                try {
                    ResultSet.getInstance().addData(sentence);
                } catch (CannotAddContentException e) {
                    LOGGER.warning(e.getMessage());
                }
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
