package ru.job4j.set;

import ru.job4j.list.DynamicArray;
import ru.job4j.list.Linked;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable {
    private DynamicArray<T> container = new DynamicArray<>();


    public void add(T e) {
        if (!container.contains(e)) {
            this.container.add(e);
        }
    }

    @Override
    public Iterator iterator() {
        return this.container.iterator();
    }
}
