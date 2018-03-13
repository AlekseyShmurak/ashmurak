package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SquareTest {

    @Test
    public void calculateTest() {
        int[] expect = new int[3];
        expect[0] = 1;
        expect[1] = 4;
        expect[2] = 9;
        Square input = new Square();
        assertThat(expect, is(input.calculate(3)));
    }
}