package com.github.kdm1jkm.enigma;

import java.util.HashMap;
import java.util.Map;

import static com.github.kdm1jkm.enigma.Constants.ALPHABET_LENGTH;
import static com.github.kdm1jkm.enigma.Constants.isValid;

public class Reflector {
    public static final String EXAMPLE = "FNUQKALPMWEGIBSHDZOXCYJTVR";
    private final Map<Character, Node<Character>> valueToNode = new HashMap<>();

    public Reflector(String mapping) {
        if (mapping.length() != ALPHABET_LENGTH)
            throw new IllegalArgumentException();

        for (int i = 0; i < mapping.length() / 2; i++) {
            char ch1 = mapping.charAt(i * 2);
            char ch2 = mapping.charAt(i * 2 + 1);
            if (!(isValid(ch1) && isValid(ch2)))
                throw new IllegalArgumentException();

            if (valueToNode.containsKey(ch1) || valueToNode.containsKey(ch2)) {
                throw new IllegalArgumentException();
            }

            Node<Character> node = new Node<>(ch1, ch2);
            valueToNode.put(node.value, node);
            valueToNode.put(node.getOther().value, node.getOther());
        }
    }

    public char convert(char ch) {
        return valueToNode.get(ch).getOther().value;
    }
}
