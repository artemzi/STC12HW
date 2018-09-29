package com.github.artemzi.hw04;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Integer[] data = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            data[i] = ThreadLocalRandom.current().nextInt(1, 100500);
        }
        MathBox mathBox = new MathBox(data);

        // second task
        System.out.println("[summator] " + mathBox.summator());

        // third task
        System.out.println("[splitter] " + mathBox.splitter(123));

        // Fifth task
        Random random = new Random();
        System.out.println("[removeElementIfExists] " + mathBox.removeElementIfExists(data[random.nextInt(100)]));

        System.out.println("[dump from ObjectBox] " + mathBox.dump());
    }
}
