package com.github.kdm1jkm.enigma;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MachineBodyTest {
    @Test
    void test() {
        MachineBody body1 = new MachineBody("AGPF", Reflector.EXAMPLE, Rotator.ROTATOR_EXAMPLES.subList(0, 3), Arrays.asList('A','A','A'));
        MachineBody body2 = new MachineBody("AGPF", Reflector.EXAMPLE, Rotator.ROTATOR_EXAMPLES.subList(0, 3), Arrays.asList('A','A','A'));

        String original = "THISISORIGINALSTRINGTESTHAHAHAHAHA";
        StringBuilder encrypt = new StringBuilder();
        for (char ch : original.toCharArray()) {
            encrypt.append(body1.convert(ch));
        }
        String encrypted = encrypt.toString();

        StringBuilder decrypt = new StringBuilder();
        for (char ch : encrypted.toCharArray()) {
            decrypt.append(body2.convert(ch));
        }
        assertEquals(original, decrypt.toString());
    }

}