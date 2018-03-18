package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class SimpleListTest {

    SimpleList<String> simpleListString;
    SimpleList<Integer> simpleListInteger;

    @Before
    public void setUp() {
        simpleListString = new SimpleList<>(5);
        simpleListInteger = new SimpleList<>(5);
        simpleListInteger.add(4);
        simpleListInteger.add(9);
        simpleListString.add("asdf");
        simpleListString.add("qwer");
    }

    @Test
    public void addTest() {
        assertThat(simpleListInteger.get(0), is(4));
        assertThat(simpleListString.get(0), is("asdf"));
    }

    @Test
    public void setTest() {
        simpleListInteger.set(0, 8);
        simpleListString.set(0, "zxcv");
        assertThat(simpleListInteger.get(0), is(8));
        assertThat(simpleListString.get(0), is("zxcv"));
    }

    @Test
    public void delete() {
        simpleListString.delete(0);
        assertThat(simpleListString.get(0), is("qwer"));
    }

    @Test
    public void get() {
        assertThat(simpleListString.get(0), is("asdf"));
    }

    @Test
    public void iterator() {
        for (String string : simpleListString) {
            System.out.println(string);
        }
    }
}