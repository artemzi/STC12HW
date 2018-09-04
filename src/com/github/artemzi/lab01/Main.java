package com.github.artemzi.lab01;

public class Main {
    public static void main(String[] args) {
        Occurrences o = new Occurrences();
        o.getOccurrences(
                new String[]{
                        "http://www.gutenberg.org/files/4280/4280-0.txt",
                        "file:data/lab01/test"
                },
                new String[]{
                        "one", "word"
                },
                "data"
        );
    }
}
