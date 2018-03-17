package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private int[] numbers;
    int index = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean rstl = false;
        if (index < numbers.length) {
            for (int i = index; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    rstl = true;
                    break;
                }
            }
        }
        return rstl;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object rslt = new Object();
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                index = i + 1;
                rslt = (Integer) numbers[i];
                break;
            }
        }
        return rslt;
    }
}
