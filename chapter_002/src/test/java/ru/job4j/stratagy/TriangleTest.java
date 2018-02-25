package ru.job4j.stratagy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void drawTest() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("0   ")
                                .append("00  ")
                                .append("000 ")
                                .append("0000")
                                .toString()
                )
        );
    }
}