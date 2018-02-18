package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {

    public String[] remove(String[] array) {
        int dublicates = 0;
        for (int i=0; i < array.length-dublicates; i++) {
            for (int j=i+1; j < array.length-dublicates; j++){
                if (array[j].equals(array[i])){
                    array[j] = array[array.length-1-dublicates];
                    j--;
                    dublicates++;
                }
            }
        }return Arrays.copyOf(array,array.length-dublicates);
    }
}
