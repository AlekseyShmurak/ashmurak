package ru.job4j.list;

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasCycle(Node first) {
        Node rabit = first;
        Node turtle = first;
        while (rabit != null && rabit.next != null && rabit.next.next != null) {
            rabit = rabit.next.next;
            turtle = turtle.next;
            if (rabit == turtle) {
                break;
            }
        }
        return rabit == turtle;
    }
}
