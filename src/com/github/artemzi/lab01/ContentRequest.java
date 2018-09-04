package com.github.artemzi.lab01;

import com.github.artemzi.hw07.MathHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class ContentRequest implements Runnable {
    private String path;
    private static final Logger LOGGER = Logger.getLogger(MathHandler.class.getName());

    ContentRequest(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        URL url = this.getUrl();
        if (url != null) {
            try (InputStream inputStream = url.openStream();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
                int data = 0;
                do {
                    data = inputStream.read();
                    byteArrayOutputStream.write(data);
                } while (data != -1);

                System.out.println(byteArrayOutputStream.toByteArray()); // TODO
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        }

    }

    private URL getUrl() {
        URL url = null;
        try {
            url = new URL(this.path);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        return url;
    }
}
