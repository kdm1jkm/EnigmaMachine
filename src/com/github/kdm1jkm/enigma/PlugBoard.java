package com.github.kdm1jkm.enigma;

public class PlugBoard {
    public static final String EXAMPLE = "AJDLCNEHGB";
    private final char[] plugs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public PlugBoard() {
    }

    public PlugBoard(String mapping) {
        for (int i = 0; i < mapping.length() / 2; i++) {
            addPlug(mapping.charAt(i * 2), mapping.charAt(i * 2 + 1));
        }
    }

    public String convert(String str) {
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            result.append(convert(ch));
        }
        return result.toString();
    }

    public char convert(char ch) {
        if (!(charToInt(ch) < plugs.length)) {
            throw new IllegalArgumentException();
        }

        return plugs[charToInt(ch)];
    }

    public void deletePlug(char ch) {
        if (!isAllocated(ch)) {
            return;
        }

        char otherChar = plugs[charToInt(ch)];
        plugs[charToInt(otherChar)] = otherChar;
        plugs[charToInt(ch)] = ch;
    }

    public void addPlug(char c1, char c2) {
        if (isAllocated(c1) || isAllocated(c2)) {
            throw new IllegalArgumentException();
        }

        plugs[charToInt(c1)] = c2;
        plugs[charToInt(c2)] = c1;
    }

    private int charToInt(char ch) {
        return ch - 'A';
    }

    private boolean isAllocated(char ch) {
        return plugs[charToInt(ch)] != ch;
    }
}
