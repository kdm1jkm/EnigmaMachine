package com.github.kdm1jkm.enigma;

public class Node<T> {
    public final T value;
    private Node<T> other;

    private Node(T value) {
        this.value = value;
    }

    public static <T> Node<T> makeNode(T value1, T value2) {
        Node<T> node1 = new Node<>(value1);
        Node<T> node2 = new Node<>(value2);

        node1.other = node2;
        node2.other = node1;

        return node1;
    }

    public Node<T> getOther() {
        return other;
    }
}
