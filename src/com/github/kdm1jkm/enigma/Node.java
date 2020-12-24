package com.github.kdm1jkm.enigma;

public class Node<T> {
    public final T value;
    private final Node<T> other;

    public Node(T thisValue, T otherValue) {
        this.value = thisValue;
        other = new Node<>(otherValue, this);
    }

    private Node(T value, Node<T> other) {
        this.value = value;
        this.other = other;
    }

    public static <T> Node<T> makeNode(T value1, T value2) {
        return new Node<>(value1, value2);
    }

    public Node<T> getOther() {
        return other;
    }
}
