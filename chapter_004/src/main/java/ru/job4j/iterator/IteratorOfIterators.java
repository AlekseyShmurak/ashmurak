package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorOfIterators {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator current = it.next();
            @Override
            public boolean hasNext() {
                while (it.hasNext()) {
                    if (current.hasNext()) {
                        break;
                    } else {
                        current = it.next();
                    }
                }
                return current.hasNext();
            }

            @Override
            public Integer next() {
                hasNext();
                return (Integer) current.next();
            }
        };
    }
}

