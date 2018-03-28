package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

@ThreadSafe
public class Linked<T> implements Iterable<T> {

    @GuardedBy("this")
    private Node first;
    @GuardedBy("this")
    private Node last;
    private int size = 0;
    private int modCount = 0;


    public synchronized void add(T value) {
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
    public synchronized boolean contains(T value) {
        boolean rslt = false;
        Node wanted = first;
        while (wanted != null) {
            if (wanted.value.equals(value)) {
                rslt = true;
                break;
            }
            wanted = wanted.next;
        }
        return rslt;
    }


    public synchronized T get(int index) {
        Node wantedNode = first;
        for (int i = 0; i < index; i++) {
            wantedNode = wantedNode.next;
        }
        return wantedNode.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node currentNode = getFirst();
            final int enteredModCouterVal = modCount;
            @Override
            public boolean hasNext() {
                if (enteredModCouterVal != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null && currentNode.getNext() != null;
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

    protected synchronized Node getLast() {
        return last;
    }

    protected synchronized Node getFirst() {
        return first;
    }

    protected synchronized void setFirst(Node first) {
        this.first = first;
    }

    private class Node {
        final T value;
        Node next;

        Node(T value) {
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
