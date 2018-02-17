package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FindLoopTest {

    @Test
    public void indexOfTest() {
        FindLoop floop = new FindLoop();
        int[] array = {2,9,15,20};
        assertThat(2,is(floop.indexOf(array,15)));
        assertThat(-1,is(floop.indexOf(array,25)));
    }
}