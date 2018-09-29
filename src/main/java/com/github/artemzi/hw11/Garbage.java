package com.github.artemzi.hw11;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * -XX:+UseSerialGC
 * -XX:+UseParallelGC
 * -XX:-UseParallelOldGC
 * -Xmx256m -XX:+UseG1GC (try it with second argument -XX:+UseStringDeduplication) or -XX:+UseConcMarkSweepGC
 *
 * code based on example from https://blog.codecentric.de/en/2014/08/string-deduplication-new-feature-java-8-update-20-2/
 * perfectly show most interesting part with G1GC and string deduplication
 */
public class Garbage {
    private static final LinkedList<String> LOTS_OF_STRINGS = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        in.nextLine(); // Manual wait until visualVM will be connected to process

        int iteration = 0;
        while (iteration < 1000) {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 1000; j++) {
                    LOTS_OF_STRINGS.add(new String("String " + j));
                }
            }
            iteration++;
            System.out.println("Survived Iteration: " + iteration);
            Thread.sleep(500);
        }
    }
}
