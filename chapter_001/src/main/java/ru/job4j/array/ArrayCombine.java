package ru.job4j.array;

public class ArrayCombine {

    public int[] combineSortedArrays(int[] first, int[] second) {
        int[] output = new int[first.length + second.length];
        int indexFirst = 0;
        int indexSecond = 0;
        for (int i = 0; i < output.length; i++) {
            if (indexSecond == second.length || (indexFirst != first.length && first[indexFirst] < second[indexSecond])) {
                output[i] = first[indexFirst];
                indexFirst++;
            } else {
                output[i] = second[indexSecond];
                indexSecond++;
            }
        }
        return output;
    }
}

