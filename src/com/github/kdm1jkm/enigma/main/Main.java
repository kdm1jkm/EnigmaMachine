package com.github.kdm1jkm.enigma.main;

import com.github.kdm1jkm.enigma.MachineBody;
import com.github.kdm1jkm.enigma.PlugBoard;
import com.github.kdm1jkm.enigma.Reflector;
import com.github.kdm1jkm.enigma.Rotator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String plugBoard;
        String reflector;
        List<String> rotators;
        String rotatorSorts;
        String rotatorInit;

        switch (args[0]) {
            case "M":
            case "Manual":
                String filename = args[1];
                try (FileInputStream inputStream = new FileInputStream(filename)) {
                    Scanner scanner = new Scanner(inputStream);

                    plugBoard = scanner.nextLine();
                    reflector = scanner.nextLine();
                    rotators = new ArrayList<>();
                    while (scanner.hasNext()) {
                        rotators.add(scanner.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                rotatorSorts = args[2];
                rotatorInit = args[3];
                break;

            case "E":
            case "Example":
                plugBoard = PlugBoard.EXAMPLE;
                reflector = Reflector.EXAMPLE;
                rotators = Rotator.ROTATOR_EXAMPLES;

                rotatorSorts = args[1];
                rotatorInit = args[2];
                break;

            default:
                printInvalid();
                return;
        }

        if (rotatorSorts.length() != rotatorInit.length()) {
            printInvalid();
            return;
        }

        List<String> selectedRotators =
                rotatorSorts.chars()
                        .map(it -> it - '1')
                        .mapToObj(rotators::get)
                        .collect(Collectors.toList());
        List<Character> selectedRotatorSettings =
                rotatorInit.chars()
                        .mapToObj(it -> (char) it)
                        .collect(Collectors.toList());

        MachineBody body = new MachineBody(plugBoard, reflector, selectedRotators, selectedRotatorSettings);

        while (true) {
            Scanner scanner = new Scanner(System.in);

            String s = scanner.nextLine();
            if (s.startsWith("/"))
                return;
            System.out.println(body.convert(s));

            for (int i = 0; i < s.length(); i++) {
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }

    private static void printInvalid() {
        System.out.println("Invalid input");
    }

    private static void printHelp() {
        System.out.println("-----Usage-----");
        System.out.println("java -jar EnigmaMachine.jar (E[xample]/M[anual] filename) rotators... rotatorsInit...");
        System.out.println("Ex) java -jar EnigmaMachine.jar E 312 ACB");
        System.out.println("Ex) java -jar EnigmaMachine.jar M setting.txt 1523 ACAB");
        System.out.println("\n---File Format");
        System.out.println("plugboard(AGBDHT -> A-G | B-D | H-T)");
        System.out.println("reflector");
        System.out.println("rotators...");
    }
}
