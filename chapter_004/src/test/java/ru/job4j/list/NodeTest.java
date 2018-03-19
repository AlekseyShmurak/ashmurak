package ru.job4j.list;

import org.junit.Before;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NodeTest {

    Node first = new Node(1);
    Node two = new Node(2);
    Node third = new Node(3);
    Node four = new Node(4);

    @Before
    public void setup() {
        first.next = two;
        two.next = third;
        third.next = four;
    }


    @Test
    public void trueCycleTest() {
        four.next = first;
        assertThat(true, is(first.hasCycle(third)));
    }

    @Test
    public void falseCycleTest() {
        assertThat(false, is(first.hasCycle(first)));
    }

    @Test
    public void selfCycleTest() {
        first.next = first;
        assertThat(true, is(first.hasCycle(first)));
    }
}