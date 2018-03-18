package ru.job4j.generic;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class SimpleList<T> implements Iterable<T> {
    private Object[] objects;
    private int currentPosition = 0;

    public SimpleList(int size) {
        this.objects = new Object[size];
    }

    public void add(T value) {
        this.objects[currentPosition++] = value;
    }

    public void set(int index, T model) {
        this.objects[index] = model;
    }

    public void delete(int index) {
        Object[] copy = new Object[objects.length];
        System.arraycopy(objects, 0, copy, 0, index);
        System.arraycopy(objects, index + 1, copy, index, objects.length - index - 1);
        objects = copy;
        currentPosition--;
    }

    public T get(int index) {
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int iteratorPosition = 0;

            @Override
            public boolean hasNext() {
                return this.iteratorPosition < objects.length;
            }

            @Override
            public T next() {
                return (T) objects[iteratorPosition++];
            }
        };
    }
}
