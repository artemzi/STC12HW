package com.github.artemzi.lab01;

import java.net.MalformedURLException;
import java.net.URL;

public class ContentRequest implements Runnable {
    private String path;

    ContentRequest(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        URL url = this.getUrl();
        // TODO: do something
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
