package ru.job4j.list;

import java.util.List;

public class SimpleStack<T> {
    private Linked<T> container = new Linked<>();

    public T poll() {
        if (container.getFirst() == null) {
            return null;
        }
        T value = container.getFirst().value;
        container.setFirst(container.getFirst().getNext());
        return value;
    }

    public void push(T value) {
        if (container.getFirst() == null) {
            container.add(value);
        } else {
            Linked.Node addedNode = container.new Node(value);
            addedNode.setNext(container.getFirst());
            container.setFirst(addedNode);
        }
    }



}
