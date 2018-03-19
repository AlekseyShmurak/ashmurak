package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    SimpleQueue<String> stringSimpleQueue = new SimpleQueue<>();

    @Test
    public void pushAndPollTest() {
        stringSimpleQueue.push("qwery");
        stringSimpleQueue.push("asdf");
        stringSimpleQueue.push("zxcv");
        assertThat(stringSimpleQueue.poll(), is("qwery"));
        assertThat(stringSimpleQueue.poll(), is("asdf"));
        assertThat(stringSimpleQueue.poll(), is("zxcv"));
        assertThat(null, is(stringSimpleQueue.poll()));

    }

}
