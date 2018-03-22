package ru.job4j.set;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleHashSetTest {
    SimpleHashSet<Integer> integerSimpleSet = new SimpleHashSet<>();

    @Test
    public void addAndContainsTest() {
        integerSimpleSet.add(5);
        integerSimpleSet.add(6);
        integerSimpleSet.add(8);
        assertThat(true, is(integerSimpleSet.contains(5)));
        assertThat(true, is(integerSimpleSet.contains(6)));
        assertThat(true, is(integerSimpleSet.contains(8)));
    }

    @Test
    public void removeTest() {
        integerSimpleSet.add(5);
        integerSimpleSet.add(6);
        assertThat(true, is(integerSimpleSet.contains(5)));
        integerSimpleSet.remove(5);
        assertThat(false, is(integerSimpleSet.contains(5)));
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void iteratorTest() {
        integerSimpleSet.add(5);
        integerSimpleSet.add(6);
        integerSimpleSet.add(8);
        Iterator iterator = integerSimpleSet.iterator();
        assertThat(true, is(iterator.hasNext()));
        assertThat(5, is(iterator.next()));
        assertThat(6, is(iterator.next()));
        integerSimpleSet.remove(5);
        iterator.hasNext();
    }
}