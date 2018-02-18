package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.*;
import static org.junit.Assert.assertThat;


public class ArrayDuplicateTest {

    @Test
    public void removeTest() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] input = {"44","55","44","66","77","55","44","55"};
        String[] expected = {"44","55","66","77"};
        assertThat(expected,arrayContainingInAnyOrder(test.remove(input)));
    }
}