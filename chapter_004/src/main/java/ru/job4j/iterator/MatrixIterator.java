package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private int[][] input;
    private int externalArrayIndex = 0;
    private int internalArrayIndex = 0;


    public MatrixIterator(int[][] input) {
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return externalArrayIndex < input.length - 1
                || (externalArrayIndex == input.length - 1 && internalArrayIndex < input[externalArrayIndex].length);
    }

    @Override
    public Object next() {
        Object rslt;
        if (input[externalArrayIndex].length > internalArrayIndex) {
            rslt = (Integer) input[externalArrayIndex][internalArrayIndex++];
        } else {
            internalArrayIndex = 0;
            rslt = input[++externalArrayIndex][internalArrayIndex++];
        }
        return rslt;
    }
}
