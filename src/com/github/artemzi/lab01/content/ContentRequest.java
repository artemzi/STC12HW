package com.github.artemzi.lab01.content;

import com.github.artemzi.lab01.exceptions.CannotAddContentException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class ContentRequest implements Runnable {
    private String path;
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());

    public ContentRequest(String path) {
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
                // TODO: apply filter for data; use search option for mark data which must be stored
                Content.getInstance().addValue(byteArrayOutputStream.toByteArray());
            } catch (IOException | CannotAddContentException e) {
                LOGGER.info(e.getMessage());
            }
        }
    }

    private URL getUrl() {
        URL url = null;
        try {
            url = new URL(this.path);
            LOGGER.info("URL created: " + url.toString());
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        return url;
    }
}
