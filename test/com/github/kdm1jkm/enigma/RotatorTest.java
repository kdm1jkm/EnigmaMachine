package com.github.kdm1jkm.enigma;

import org.junit.jupiter.api.Test;

import static com.github.kdm1jkm.enigma.Constants.ALPHABET_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RotatorTest {
    @Test
    void forwardTest() {
        Rotator rotator = new Rotator("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        String s = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            assertEquals(s.charAt(i), rotator.convertForward((char) ('A' + i)));
        }
    }

    @Test
    void backwardTest() {
        Rotator rotator = new Rotator("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        String s = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            assertEquals((char) (i + 'A'), rotator.convertBackward(s.charAt(i)));
        }
    }
}