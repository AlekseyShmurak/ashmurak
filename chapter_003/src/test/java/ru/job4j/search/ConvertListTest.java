package ru.job4j.search;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class ConvertListTest {

    @Test
    public void toListTest() {
        int[][] input = {{1, 2, 3}, {3, 4, 5}};
        List<Integer> output = ConvertList.toList(input);
        Integer[] expect = {1, 2, 3, 3, 4, 5};
        assertThat(expect, is(output.toArray()));
    }

    @Test
    public void toArrayTest() {
        int[][] expect = {{1, 2, 3}, {3, 4, 5}, {6, 0, 0}};
        int[][] input = {{1, 2, 3, 3, 4, 5, 6}};

        assertThat(expect, is(ConvertList.toArray(ConvertList.toList(input), 3)));
    }

    @Test
    public void convertTest() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> expect = new ArrayList<>();
        for (int i = 1; i < 7; i++){
            expect.add(i);
        }

        assertThat(expect, is(ConvertList.convert(list)));
    }

}