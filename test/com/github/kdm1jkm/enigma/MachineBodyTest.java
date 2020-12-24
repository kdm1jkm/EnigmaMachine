package com.github.kdm1jkm.enigma;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MachineBodyTest {
    @Test
    void test() {
        MachineBody body1 = new MachineBody(Reflector.EXAMPLE, Rotator.ROTATOR_EXAMPLE.subList(0, 3));
        MachineBody body2 = new MachineBody(Reflector.EXAMPLE, Rotator.ROTATOR_EXAMPLE.subList(0, 3));
        body1.addPlug('A', 'G');
        body1.addPlug('P', 'F');
        body2.addPlug('A', 'G');
        body2.addPlug('P', 'F');

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