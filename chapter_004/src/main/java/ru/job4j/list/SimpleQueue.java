package ru.job4j.list;

public class SimpleQueue<T> {
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
        container.add(value);
    }
}
