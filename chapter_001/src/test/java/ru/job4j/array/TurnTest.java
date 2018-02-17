package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TurnTest {

    @Test
    public void backTestWithEvenNumberOfElements() {
        Turn test = new Turn();
        int[] input = {1,2,3,4};
        int[] output = {4,3,2,1};
        assertThat(output,is(test.back(input)));
    }

    @Test
    public void backTestWithOddNumberOfElements() {
        Turn test = new Turn();
        int[] input = {1,2,3,4,5};
        int[] output = {5,4,3,2,1};
        assertThat(output,is(test.back(input)));
    }

}