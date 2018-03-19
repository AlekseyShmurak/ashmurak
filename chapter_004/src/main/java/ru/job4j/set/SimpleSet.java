package ru.job4j.set;

import ru.job4j.list.DynamicArray;
import ru.job4j.list.Linked;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable {
    private Linked<T> container = new Linked<>();


    public void add(T e) {
        boolean isUnique = true;
        for (T value : this.container) {
            if (value.equals(e)) {
                isUnique = false;
                break;
            }
        }
        if (isUnique) {
            this.container.add(e);
        }
    }

    @Override
    public Iterator iterator() {
        return this.container.iterator();
    }
}
