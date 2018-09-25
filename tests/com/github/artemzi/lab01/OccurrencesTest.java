package com.github.artemzi.lab01;

import com.github.artemzi.lab01.main.Occurrences;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

class OccurrencesTest {
    private static final String RESULT_FILE_NAME = "RESULT_TEST";
    private static final String RESULT_FILE_WITH_PATH = "data/lab01/RESULT_TEST";
    private static final Logger LOGGER = Logger.getLogger(OccurrencesTest.class.getName());
    private Occurrences o;

    @BeforeEach void setUp() {
        o = new Occurrences();
    }

    @AfterAll static void cleanUp() {
        File file = new File(RESULT_FILE_WITH_PATH);
        assert file.delete();
        LOGGER.info("Result file was deleted.");
    }

    @Test void resultFileCanBeCreated() {
        getResult();
        File result =  new File(RESULT_FILE_WITH_PATH);
        Assertions.assertTrue(result.isFile());
    }

    @Test void wordsWasFounded() {
        getResult();
        String content = "";
        try {
            content = readFile(RESULT_FILE_WITH_PATH, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        LOGGER.info("Content from file received");
        Assumptions.assumeTrue(content.contains("War".toLowerCase()) | content.contains("Peace".toLowerCase()));
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        LOGGER.info("Start reading a file...");
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private void getResult() {
        LOGGER.info("getOccurrences was called from testCase");
        try {
            o.getOccurrences(
                    new String[]{"https://en.wikipedia.org/wiki/War_and_Peace"},
                    new String[]{"War", "Peace"},
                    RESULT_FILE_NAME
            );
        } catch (InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
