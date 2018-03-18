package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorOfIterators {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator currentIterator = it.next();
            @Override
            public boolean hasNext() {
                while (it.hasNext()) {
                    if (currentIterator.hasNext()) {
                        break;
                    } else {
                        currentIterator = it.next();
                    }
                }
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                while (hasNext()) {
                    if (currentIterator.hasNext()) {
                        break;
                    }
                }
                return (Integer) currentIterator.next();
            }
        };
    }
}

