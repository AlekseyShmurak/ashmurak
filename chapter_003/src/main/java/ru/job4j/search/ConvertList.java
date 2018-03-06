package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {
    public static List<Integer> toList (int[][] array) {
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (Integer integer : array[i]) {
                output.add(integer);
            }
        }
        return output;
    }

    public static int[][] toArray (List<Integer> list, int rows) {
        int[][] output = new int[list.size() % rows == 0 ? list.size()/rows : list.size()/rows + 1][];
        int listIndex = 0;
        for (int i = 0; i < output.length; i++){
            output[i] = new int[rows];
            for (int j = 0; j < output[i].length; j++) {
                if (listIndex != list.size()){
                    output[i][j] = list.get(listIndex);
                    listIndex++;
                }
            }
        }
        return output;
    }

    public static List<Integer> convert (List<int[]> list) {
        List<Integer> output = new ArrayList<>();
        for(int[] array : list) {
            for (int value : array) {
                output.add(value);
            }
        }
        return output;
    }
}


