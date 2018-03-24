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

    public boolean isBinary() {
        boolean rslt = true;
        NodeIterator nods = new NodeIterator();
        while (nods.hasNext()) {
            if (nods.next().leaves().size() > 2) {
                rslt = false;
                break;
            }
        }
        return rslt;
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
        return new Iterator<E>() {
            NodeIterator nods = new NodeIterator();

            public boolean hasNext() {
                return nods.hasNext();
            }

            @Override
            public E next() {
                return nods.next().getValue();
            }
        };

    }

    private class NodeIterator implements Iterator<Node<E>> {
        Queue<Node<E>> values = new LinkedList<>();
        final int expectMod = modCount;

        public NodeIterator() {
            this.values.offer(root);
        }

        @Override
            public boolean hasNext() {
                if (expectMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !values.isEmpty();
            }

            @Override
            public Node<E> next() {
                Node<E> rstl = null;
                if (hasNext()) {
                    rstl = values.poll();
                    for (Node<E> child : rstl.leaves()) {
                        values.offer(child);
                    }
                }
                return rstl;
            }
    }
}
