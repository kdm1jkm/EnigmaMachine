package com.github.kdm1jkm.enigma;

import org.junit.jupiter.api.Test;

import static com.github.kdm1jkm.enigma.Node.makeNode;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void node(){
        Node<Character> node1 = new Node<>('a','b');
        Node<Character> node2 = node1.getOther();

        assertEquals(node1, node2.getOther());
        assertEquals(node1.getOther(), node2);
        assertEquals('a', node1.value);
        assertEquals('b', node1.getOther().value);
        assertEquals('a', node2.getOther().value);
        assertEquals('b', node2.value);
    }

}