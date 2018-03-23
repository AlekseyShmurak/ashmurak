package ru.job4j.iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private int[][] input;
    private int columnInd = 0;
    private int rowInd = 0;


    public MatrixIterator(int[][] input) {
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return columnInd < input.length - 1
                || (columnInd == input.length - 1 && rowInd < input[columnInd].length);
    }

    @Override
    public Object next() {
        Object rslt;
        if (input[columnInd].length > rowInd) {
            rslt = (Integer) input[columnInd][rowInd++];
        } else {
            rowInd = 0;
            rslt = input[++columnInd][rowInd++];
        }
        return rslt;
    }
}
