package com.github.kdm1jkm.enigma;

import java.util.ArrayList;
import java.util.List;

public class MachineBody {
    final Reflector reflector;
    final PlugBoard board;
    final List<Rotator> rotators;

    public MachineBody(String plugBoardMapping, String reflectMapping, List<String> rotatorMappings, List<Character> rotatorSettings) {
        reflector = new Reflector(reflectMapping);
        board = new PlugBoard(plugBoardMapping);
        rotators = new ArrayList<>();
        for (int i = 0; i < rotatorMappings.size(); i++) {
            String s = rotatorMappings.get(i);
            Rotator rotator = new Rotator(s);
            rotators.add(rotator);
            rotator.setRotation(rotatorSettings.get(i));
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

    public String convert(String str) {
        StringBuilder result = new StringBuilder();

        for (char ch : str.toCharArray()) {
            result.append(convert(ch));
        }

        return result.toString();
    }

    public void deletePlug(char ch) {
        board.deletePlug(ch);
    }

    public void addPlug(char c1, char c2) {
        board.addPlug(c1, c2);
    }

}

