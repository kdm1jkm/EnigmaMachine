package com.github.kdm1jkm.enigma;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlugBoardTest {
    @Test
    void convertString() {
        PlugBoard board = new PlugBoard();
        String str = "ABCDEFG";
        board.addPlug('A', 'G');
        board.addPlug('B', 'H');

        assertEquals("GHCDEFA", board.convert(str));
    }

    @Test
    void convertChar() {
        PlugBoard board = new PlugBoard();
        for (char ch : Constants.ALL_ALPHABET.toCharArray()) {
            assertEquals(ch, board.convert(ch));
        }
    }

    @Test
    void deletePlug() {
        PlugBoard board = new PlugBoard();
        board.addPlug('A', 'C');
        board.addPlug('B', 'D');
        board.addPlug('F', 'G');
        board.deletePlug('B');
        board.deletePlug('A');

        assertEquals('A', board.convert('A'));
        assertEquals('B', board.convert('B'));
        assertEquals('C', board.convert('C'));
        assertEquals('D', board.convert('D'));

        assertEquals('G', board.convert('F'));
    }

    @Test
    void addPlug() {
        PlugBoard board = new PlugBoard();
        board.addPlug('A', 'C');
        assertEquals('C', board.convert('A'));
        assertEquals('A', board.convert('C'));
        assertEquals('B', board.convert('B'));
    }
}