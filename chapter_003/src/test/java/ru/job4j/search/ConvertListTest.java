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
        ConvertList convertList = new ConvertList();
        int[][] input = {{1, 2, 3}, {3, 4, 5}};
        List<Integer> output = convertList.toList(input);
        Integer[] expect = {1, 2, 3, 3, 4, 5};
        assertThat(expect, is(output.toArray()));
    }

    @Test
    public void toArrayTest() {
        ConvertList convertList = new ConvertList();
        int[][] expect = {{1, 2, 3}, {3, 4, 5}, {6, 0, 0}};
        int[][] input = {{1, 2, 3, 3, 4, 5, 6}};

        assertThat(expect, is(convertList.toArray(convertList.toList(input), 3)));
    }

    @Test
    public void convertTest() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(2);
        expect.add(3);
        expect.add(4);
        expect.add(5);
        expect.add(6);
        assertThat(expect, is(convertList.convert(list)));
    }

}