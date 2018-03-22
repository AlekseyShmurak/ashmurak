package ru.job4j.set;

import ru.job4j.map.SimpleHashTable;


import java.util.Iterator;

public class SimpleHashSet<T> implements Iterable {
    private SimpleHashTable<T, Object> container = new SimpleHashTable<>();
    static final private Object EMPTY = null;

    public void add(T e) {
        this.container.insert(e, EMPTY);
    }

    public boolean contains(T e) {
        return container.containsKey(e);
    }

    public boolean remove(T e) {
        return container.delete(e);
    }

    @Override
    public Iterator iterator() {
        return this.container.getKeyIterator();
    }
}

