package ru.job4j.list;

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasCycle(Node first) {
        Node nextNode = first;
        do {
            nextNode = nextNode.next;
        } while (nextNode.next != null && nextNode != first);
        return nextNode == first;
    }
}
