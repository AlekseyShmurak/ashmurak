package ru.job4j.list;

import org.junit.Before;
        import org.junit.Test;
        import static org.hamcrest.core.Is.is;
        import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    SimpleStack<String> stringSimpleStack = new SimpleStack<>();

    @Test
    public void pushAndPollTest() {
        stringSimpleStack.push("qwery");
        stringSimpleStack.push("asdf");
        stringSimpleStack.push("zxcv");
        assertThat(stringSimpleStack.poll(), is("zxcv"));
        assertThat(stringSimpleStack.poll(), is("asdf"));
        assertThat(stringSimpleStack.poll(), is("qwery"));
        assertThat(null, is(stringSimpleStack.poll()));

    }

}