package com.github.kdm1jkm.enigma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.kdm1jkm.enigma.Constants.ALPHABET_LENGTH;

public class Rotator {
    private final Map<Character, Node<Character>> forward = new HashMap<>();
    private final Map<Character, Node<Character>> backward = new HashMap<>();
    private int rotated = 0;

    public Rotator(String mapping) {
        if (mapping.length() != ALPHABET_LENGTH)
            throw new IllegalArgumentException();

        Set<Character> poppedChar = new HashSet<>();

        for (int i = 0; i < mapping.length(); i++) {
            char ch = mapping.charAt(i);
            if (poppedChar.contains(ch) || !('A' <= ch && ch <= 'Z'))
                throw new IllegalArgumentException();
            poppedChar.add(ch);

            Node<Character> node = new Node((char) ('A' + i), ch);
            forward.put(node.value, node);
            backward.put(node.getOther().value, node.getOther());
        }
    }

    private int push(int num, int push, int start, int len) {
        return ((num + len + push - start) % len) + start;
    }

    public char convertForward(char ch) {
        char pushed = (char) push(ch, rotated, 'A', ALPHABET_LENGTH);
        Character transformed = forward.get(pushed).getOther().value;
        return (char) push(transformed, -rotated, 'A', ALPHABET_LENGTH);
    }

    public char convertBackward(char ch) {
        char pushed = (char) push(ch, rotated, 'A', ALPHABET_LENGTH);
        Character transformed = backward.get(pushed).getOther().value;
        return (char) push(transformed, -rotated, 'A', ALPHABET_LENGTH);
    }

    public void rotate() {
        rotated = push(rotated, -1, 0, ALPHABET_LENGTH);
    }

    public char getRotation() {
        return (char) ('A' + rotated);
    }

    public void setRotation(char ch) {
        rotated = ch - 'A';
    }

}
