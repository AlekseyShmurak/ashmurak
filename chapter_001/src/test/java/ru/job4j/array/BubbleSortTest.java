package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BubbleSortTest {

    @Test
    public void sortTest() {
        BubbleSort test = new BubbleSort();
        int[] input = {5,1,3,4,2};
        int[] inputSort = {5,1,2,3,4};
        int[] expected = {1,2,3,4,5};
        assertThat(expected,is(test.sort(inputSort)));
        assertThat(expected,is(test.sort(input)));

    }
}