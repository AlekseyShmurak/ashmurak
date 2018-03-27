package ru.job4j.threads;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StringAnalyzerTest {
    StringAnalyzer space = new StringAnalyzer("qewr qer qewr qer req");

    @Test
    public void threadsTest() {
        space.spaceThead.start();
        space.wordsTread.start();
    }
}