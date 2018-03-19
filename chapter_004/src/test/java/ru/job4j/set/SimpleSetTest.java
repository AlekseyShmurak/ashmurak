package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    SimpleSet<String> simpleSet = new SimpleSet<>();

    @Test
    public void addAndIteratorTest() {
        simpleSet.add("asdf");
        simpleSet.add("qwer");
        simpleSet.add("qwer");
        simpleSet.add("asdf");
        Iterator<String> iterator = simpleSet.iterator();
        assertThat("asdf", is(iterator.next()));
        assertThat("qwer", is(iterator.next()));
        assertThat(false, is(iterator.hasNext()));

    }

}