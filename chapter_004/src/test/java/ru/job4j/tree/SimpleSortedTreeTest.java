package ru.job4j.tree;

import org.junit.Test;
import ru.job4j.tree.SimpleSortedTree;

import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSortedTreeTest {
    SimpleSortedTree<Integer> tree = new SimpleSortedTree(8);

    @Test
    public void addAndGetTest() {
        assertThat(false, is(tree.add(8)));
        tree.add(7);
        tree.add(4);
        tree.add(3);
        assertThat(3, is(tree.get(3)));
        assertThat(7, is(tree.get(7)));
        assertThat(4, is(tree.get(4)));
        assertThat(null, is(tree.get(9)));
        assertThat(false, is(tree.add(7)));
        assertThat(false, is(tree.add(4)));
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void iteratorTest() {
        tree.add(8);
        tree.add(4);
        tree.add(2);
        tree.add(3);
        tree.add(1);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        tree.add(12);
        tree.add(10);
        tree.add(9);
        Iterator<Integer> iter = tree.iterator();
        assertThat(1, is(iter.next()));
        assertThat(2, is(iter.next()));
        assertThat(3, is(iter.next()));
        assertThat(4, is(iter.next()));
        assertThat(5, is(iter.next()));
        assertThat(6, is(iter.next()));
        assertThat(7, is(iter.next()));
        assertThat(8, is(iter.next()));
        assertThat(9, is(iter.next()));
        assertThat(10, is(iter.next()));
        tree.add(15);
        iter.hasNext();
    }

}