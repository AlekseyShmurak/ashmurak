package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Linked<T> implements Iterable<T> {
    private Node first;
    private Node last;
    int size = 0;
    private int modCount = 0;


    public void add(T value) {
        if (first == null) {
            first = new Node(value);
            last = first;
            size++;
        } else {
            Node addedNode = new Node(value);
            last.setNext(addedNode);
            last = addedNode;
            size++;
        }
        modCount++;
    }

    public T get(int index) {
        Node wantedNode = first;
        for (int i = 0; i < index; i++) {
            wantedNode = wantedNode.next;
        }
        return wantedNode.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node currentNode = first;
            final int enteredModCouterVal = modCount;
            @Override
            public boolean hasNext() {
                if (enteredModCouterVal != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode.getNext() != null;
            }

            @Override
            public T next() {
                if (enteredModCouterVal != modCount) {
                    throw new ConcurrentModificationException();
                }
                T value = currentNode.value;
                currentNode = currentNode.getNext();
                return value;
            }
        };
    }


    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }
    }


}
