package com.github.artemzi.lab01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class ContentRequest implements Runnable {
    private String path;
    private static final Logger LOGGER = Logger.getLogger(ContentRequest.class.getName());

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

                boolean added = Content.getInstance().addValue(byteArrayOutputStream.toByteArray());
                if (added) { // TODO: add exception?
                    LOGGER.info("New value was added to Content" + byteArrayOutputStream.toByteArray().hashCode());
                    Content.getInstance().done(); // Remove one job from WaitGroup
                } else {
                    LOGGER.warning("Can't add content");
                }
            } catch (IOException e) {
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
