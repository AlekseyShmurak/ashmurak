package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCombineTest {

    @Test
    public void combineSortedArrays() {
        ArrayCombine test = new ArrayCombine();
        int[] expect = {1, 2, 3, 3, 4, 5, 5, 6};
        int[] first = {1, 3, 4, 5};
        int[] second = {2, 3, 5, 6};
        assertThat(expect,is(test.combineSortedArrays(first,second )));
    }
}