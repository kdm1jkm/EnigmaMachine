package com.github.kdm1jkm.enigma;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Constants {
    public static final int ALPHABET_LENGTH = 26;
    public static final String ALL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    private Constants() {
    }

    public static List<Character> getShuffledCharacter() {
        Random random = new Random();
        List<Character> plugBoard = ALL_ALPHABET.chars().
                mapToObj(it -> (char) it)
                .collect(Collectors.toList());
        for (int i = 0; i < plugBoard.size(); i++) {
            Character temp = plugBoard.get(i);
            int randomNum = random.nextInt(plugBoard.size());
            plugBoard.set(i, plugBoard.get(randomNum));
            plugBoard.set(randomNum, temp);
        }
        return plugBoard;
    }

    public static String getShuffledAlphabets() {
        StringBuilder result = new StringBuilder();
        getShuffledCharacter()
                .forEach(result::append);
        return result.toString();
    }

    static boolean isValid(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }
}
