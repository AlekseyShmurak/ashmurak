package ru.job4j.tree;

import java.util.*;

public class SimpleSortedTree<E extends Comparable<E>> implements Iterable<E> {
    Node<E> root;
    int modCount = 0;

    public SimpleSortedTree(E value) {
        this.root = new Node<>(value);
    }

    private SimpleSortedTree(Node<E> root) {
        this.root = root;
    }

    public boolean add(E value) {
        return addNode(new Node<>(value));
    }

    private boolean addNode(Node<E> input) {
        boolean rslt = false;
        if (root.getValue().compareTo(input.getValue()) > 0) {
            if (root.left() == null) {
                rslt = true;
                root.left = input;
            } else {
                SimpleSortedTree<E> next = new SimpleSortedTree<>(root.left());
                rslt = next.addNode(input);
            }
        } else if (root.getValue().compareTo(input.getValue()) < 0) {
            if (root.right() == null) {
                rslt = true;
                root.right = input;
            } else {
                SimpleSortedTree<E> next = new SimpleSortedTree<>(root.right());
                rslt = next.addNode(input);
            }
        }
        if (rslt) {
            modCount++;
        }
        return rslt;
    }

    public E get(E value) {
        E rslt = null;
        Node<E> node = getNode(value);
        if (node != null) {
            rslt = node.getValue();
        }
        return rslt;
    }

    private Node<E> getNode(E value) {
        Node<E> rslt = null;
        if (root != null) {
            if (root.getValue().equals(value)) {
                rslt = root;
            } else if (root.getValue().compareTo(value) > 0) {
                SimpleSortedTree<E> next = new SimpleSortedTree<>(root.left());
                rslt = next.getNode(value);
            } else {
                SimpleSortedTree<E> next = new SimpleSortedTree<>(root.right());
                rslt = next.getNode(value);
            }
        }
        return rslt;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<Node<E>> iter = new NodeIterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public E next() {
                return iter.next().getValue();
            }
        };
    }

    private class NodeIterator implements Iterator<Node<E>> {
        Deque<Node<E>> stack = new LinkedList<>();
        final int expectMod = modCount;

        public NodeIterator() {
            fillStack(root);
        }

        @Override
        public boolean hasNext() {
            if (expectMod != modCount) {
                throw new ConcurrentModificationException();
            }
            return !stack.isEmpty();
        }

        @Override
        public Node<E> next() {
            Node<E> rslt = null;
            if (hasNext()) {
                rslt = stack.poll();
                if (rslt.right != null) {
                    fillStack(rslt.right);
                }
            }
           return rslt;
        }

        private void fillStack(Node<E> node) {
            Node<E> next = node;
            while (next != null) {
                stack.push(next);
                next = next.left;
            }
        }

    }

    private class Node<E> {
        final private E value;
        private Node<E> left;
        private Node<E> right;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> left() {
            return left;
        }

        public Node<E> right() {
            return right;
        }
    }
}
