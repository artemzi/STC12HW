package com.github.artemzi.lab01.content;

import com.github.artemzi.lab01.utils.TypeConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ContentParser {
    private static final Logger LOGGER = Logger.getLogger(ContentParser.class.getName());

    private ContentParser(){}

    public static List<String> parse() {
        try {
            Content.getInstance().await();
        } catch (InterruptedException e) {
            LOGGER.info("Counter.await was broken.");
        }

        for (Byte[] data : Content.getInstance().getData()) {
            System.out.println(new String(TypeConverter.toPrimitives(data), StandardCharsets.UTF_8));
        }

        return new ArrayList<String>();
    }
}
