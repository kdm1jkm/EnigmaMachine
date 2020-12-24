package com.github.kdm1jkm.enigma;

import java.util.*;

import static com.github.kdm1jkm.enigma.Constants.ALPHABET_LENGTH;

public class Rotator {
    public static final List<String> ROTATOR_EXAMPLE = Arrays.asList(
            "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
            "AJDKSIRUXBLHWTMCQGZNPYFVOE",
            "BDFHJLCPRTXVZNYEIWGAKMUSQO",
            "ESOVPZJAYQUIRHXLNFTGKDCMWB",
            "VZBRGITYUPSDNHLXAWMJQOFECK"
    );

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

            Node<Character> node = new Node<>((char) ('A' + i), ch);
            forward.put(node.value, node);
            backward.put(node.getOther().value, node.getOther());
        }
    }

    private int push(int num, int push, int start) {
        return ((num + ALPHABET_LENGTH + push - start) % ALPHABET_LENGTH) + start;
    }

    public char convertForward(char ch) {
        char pushed = (char) push(ch, rotated, 'A');
        Character transformed = forward.get(pushed).getOther().value;
        return (char) push(transformed, -rotated, 'A');
    }

    public char convertBackward(char ch) {
        char pushed = (char) push(ch, rotated, 'A');
        Character transformed = backward.get(pushed).getOther().value;
        return (char) push(transformed, -rotated, 'A');
    }

    /***
     * rotate self once.
     * @return true if rotation is reset to 0.
     */
    public boolean rotate() {
        rotated = push(rotated, -1, 0);
        return rotated == 0;
    }

    public char getRotation() {
        return (char) ('A' + rotated);
    }

    public void setRotation(char ch) {
        rotated = ch - 'A';
    }

}
