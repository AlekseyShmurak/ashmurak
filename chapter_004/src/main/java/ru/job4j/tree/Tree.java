package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.ConcurrentModificationException;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    Node<E> root;
    int modCount = 0;

    public Tree(E value) {
        this.root = new Node(value);
    }

    private Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rslt = false;
        Node<E> parentNode = findBy(parent).get();
        Tree<E> parentTree = new Tree<E>(parentNode);
        if (Optional.empty().equals(parentTree.findBy(child))) {
            parentNode.add(new Node<>(child));
            modCount++;
            rslt = true;
        }
        return rslt;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        Queue<Node<E>> values = new LinkedList<>();
        values.offer(root);
        return new Iterator<E>() {
            final int expectMod = modCount;
            @Override
            public boolean hasNext() {
                if (expectMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !values.isEmpty();
            }

            @Override
            public E next() {
                E rstl = null;
                if (hasNext()) {
                    Node<E> current = values.poll();
                    rstl = current.getValue();
                    for (Node<E> child : current.leaves()) {
                        values.offer(child);
                    }
                }
                return rstl;
            }
        };
    }
}
