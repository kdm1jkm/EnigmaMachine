package com.github.kdm1jkm.enigma;

import java.util.HashMap;
import java.util.Map;

import static com.github.kdm1jkm.enigma.Constants.ALPHABET_LENGTH;

public class Reflector {
    public static final String EXAMPLE = "FNUQKALPMWEGIBSHDZOXCYJTVR";
    private final Map<Character, Node<Character>> valueToNode = new HashMap<>();

    public Reflector(String mapping) {
        if (mapping.length() != ALPHABET_LENGTH)
            throw new IllegalArgumentException();

        for (int i = 0; i < mapping.length(); i++) {
            char ch = mapping.charAt(i);
            if (!('A' <= ch && ch <= 'Z'))
                throw new IllegalArgumentException();

            if (valueToNode.containsKey(ch)) {
                if (valueToNode.get(ch).getOther().value != (char) ('A' + i)) {
                    throw new IllegalArgumentException();
                } else {
                    continue;
                }
            }

            Node<Character> node = new Node<>((char) ('A' + i), ch);
            valueToNode.put(node.value, node);
            valueToNode.put(node.getOther().value, node.getOther());
        }
    }

    public char convert(char ch) {
        return valueToNode.get(ch).getOther().value;
    }
}
