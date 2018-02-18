package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StringContainsTest {

    @Test
    public void containsTestTrue() {
        StringContains test = new StringContains();
        assertThat(true,is(test.contains("qwerty","rty")));
    }

    @Test
    public void containsTestFalse() {
        StringContains test = new StringContains();
        assertThat(false,is(test.contains("qwerty","eert")));
    }
}