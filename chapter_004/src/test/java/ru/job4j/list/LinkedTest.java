package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LinkedTest {
    Linked<String> stringLinked = new Linked<>();
    Linked<Integer> integerLinked = new Linked<>();

    @Before
    public void setup() {
        stringLinked.add("qwer");
        stringLinked.add("asdf");
        stringLinked.add("zxcv");
        stringLinked.add("asdf");
        integerLinked.add(55);
        integerLinked.add(77);
    }

    @Test
    public void addAndGetTest() {
        assertThat(stringLinked.get(0), is("qwer"));
        assertThat(stringLinked.get(1), is("asdf"));
        assertThat(stringLinked.get(2), is("zxcv"));
        assertThat(integerLinked.get(0), is(55));
        assertThat(integerLinked.get(1), is(77));

    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void iterator() {
        Iterator<Integer> integerIterator = integerLinked.iterator();
        integerIterator.hasNext();
        assertThat(integerIterator.next(), is(55));
        assertThat(integerIterator.next(), is(77));
        integerLinked.add(66);
        integerIterator.hasNext();
    }
}