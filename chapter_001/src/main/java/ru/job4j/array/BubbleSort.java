package ru.job4j.array;

public class BubbleSort {

    public int[] sort(int[] array) {
        int cycle = 0;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length - cycle; i++) {
                if (array[i - 1] > array[i]) {
                    isSorted = false;
                    array[i] = array[i] + array[i - 1];
                    array[i - 1] = array[i] - array[i - 1];
                    array[i] = array[i] - array[i - 1];
                }
            }
            cycle++;
        }
        return array;
    }
}
