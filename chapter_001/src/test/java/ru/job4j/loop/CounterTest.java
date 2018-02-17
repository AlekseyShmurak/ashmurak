package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class CounterTest {

    @Test
    public void addTest() {
        Counter counter = new Counter();
        assertThat(24,is(counter.add(5,11)));
    }

}