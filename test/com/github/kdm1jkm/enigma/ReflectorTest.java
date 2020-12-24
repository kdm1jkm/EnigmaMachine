package com.github.kdm1jkm.enigma;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectorTest {
    @Test
    void testReflect() {
        Reflector reflector = new Reflector(Reflector.EXAMPLE);
        assertEquals('F', reflector.convert('A'));
        assertEquals('W', reflector.convert('J'));
        assertEquals('A', reflector.convert('F'));
        assertEquals('J', reflector.convert('W'));
    }
}
