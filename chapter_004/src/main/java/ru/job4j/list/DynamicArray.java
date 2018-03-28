package ru.job4j.list;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.ConcurrentModificationException;

@ThreadSafe
public class DynamicArray<T> implements Iterable<T> {
    @GuardedBy("this")
    private T[] container;
    private int currentPosition = 0;
    private int modCount = 0;

    private synchronized T[] getContainer() {
        return container;
    }

    public DynamicArray() {
        this.container = (T[]) new Object[10];
    }

    public synchronized void add(T value) {
        if (currentPosition < this.container.length) {
            this.container[currentPosition++] = value;
        } else {
            T[] extendedValues = (T[]) new Object[currentPosition * 3 / 2];
            System.arraycopy(this.container, 0, extendedValues, 0, container.length);
            this.container = extendedValues;
            this.container[currentPosition++] = value;
        }
        modCount++;
    }

    public boolean contains(T value) {
        boolean rslt = false;
        for (T current : this) {
            if (current.equals(value)) {
                rslt = true;
                break;
            }
        }
        return rslt;
    }

    public synchronized T get(int index) {
        return container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int iteratorPosition = 0;
            final int enteredModCouterVal = modCount;
            @Override
            public synchronized boolean hasNext() {
                if (enteredModCouterVal != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorPosition < getContainer().length && getContainer()[iteratorPosition] != null;
            }

            @Override
            public synchronized T next() {
                if (enteredModCouterVal != modCount) {
                    throw new ConcurrentModificationException();
                }
                return getContainer()[iteratorPosition++];
            }
        };
    }
}


