package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    private int[] numbers;
    int index = 0;

    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean rstl = false;
        if (index < numbers.length) {
            for (int i = index; i < numbers.length; i++) {
                if (isPrime(numbers[i])) {
                    index = i;
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
        return numbers[index++];
    }

    private boolean isPrime(int number) {
        boolean rslt = true;
        if (number == 1) {
            rslt = false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                rslt = false;
                break;
            }
        }
        return rslt;
    }
}

