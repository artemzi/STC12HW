package com.github.artemzi.lab01;

import com.github.artemzi.lab01.main.Occurrences;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

class OccurrencesTest {
    private static final String RESULT_FILE_NAME = "RESULT_TEST";
    private static final String RESULT_FILE_WITH_PATH = "data/lab01/RESULT_TEST";
    private static Occurrences o;

    @BeforeAll static void setUp() {
        o = new Occurrences();
    }

    @AfterAll static void cleanUp() {
        File file = new File(RESULT_FILE_WITH_PATH);
        assert file.delete();
    }

    @Test void resultFileCanBeCreated() {
        try {
            o.getOccurrences(
                        new String[] { "https://en.wikipedia.org/wiki/War_and_Peace" },
                        new String[]{ "War", "Peace" },
                        RESULT_FILE_NAME
                );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
