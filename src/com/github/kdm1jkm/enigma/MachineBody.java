package com.github.kdm1jkm.enigma;

import java.util.ArrayList;
import java.util.List;

public class MachineBody {
    final Reflector reflector;
    final PlugBoard board;
    final List<Rotator> rotators;

    public MachineBody(String reflectMapping, List<String> rotatorMappings) {
        reflector = new Reflector(reflectMapping);
        board = new PlugBoard();
        rotators = new ArrayList<>();
        for (String s : rotatorMappings) {
            rotators.add(new Rotator(s));
        }
    }

    public char convert(char ch) {
        char result = board.convert(ch);

        for (Rotator rotator : rotators) {
            result = rotator.convertForward(result);
        }
        result = reflector.convert(result);
        for (int i = 0; i < rotators.size(); i++) {
            result = rotators.get(rotators.size() - i - 1).convertBackward(result);
        }

        for (Rotator rotator : rotators) {
            if (!rotator.rotate()) break;
        }

        result = board.convert(result);

        return result;
    }

    public void deletePlug(char ch) {
        board.deletePlug(ch);
    }

    public void addPlug(char c1, char c2) {
        board.addPlug(c1, c2);
    }


}

