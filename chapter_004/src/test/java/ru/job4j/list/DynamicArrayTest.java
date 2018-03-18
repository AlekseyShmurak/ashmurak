package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DynamicArrayTest {
    DynamicArray<String> stringDynamicArray = new DynamicArray();
    DynamicArray<Integer> integerDynamicArray = new DynamicArray<>();

    @Before
    public void setup() {
        stringDynamicArray.add("qwer");
        stringDynamicArray.add("asdf");
        integerDynamicArray.add(55);
        integerDynamicArray.add(66);
    }

    @Test
    public void add() {
        stringDynamicArray.add("zxcv");
        integerDynamicArray.add(77);
        assertThat(stringDynamicArray.get(2), is("zxcv"));
        assertThat(integerDynamicArray.get(2), is(77));
    }

    @Test
    public void get() {
        assertThat(integerDynamicArray.get(0), is(55));
        assertThat(stringDynamicArray.get(0), is("qwer"));
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void iterator() {
        Iterator<Integer> integerIterator = integerDynamicArray.iterator();
        integerIterator.hasNext();
        assertThat(integerIterator.next(), is(55));
        integerDynamicArray.add(66);
        integerIterator.hasNext();
    }
}