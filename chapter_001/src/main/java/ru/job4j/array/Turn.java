package ru.job4j.array;

public class Turn {

    public int[] back(int[] array) {
        int counter = array.length % 2 == 0 ? array.length/2 : (array.length - 1)/2;
        for (int i = 0; i < counter; i++) {
            array[i] = array[i] + array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i] - array[array.length - 1 - i];
            array[i] = array[i] - array[array.length - 1 - i];
        }
        return array;
    }
}

