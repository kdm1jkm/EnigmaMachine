package com.github.kdm1jkm.enigma.main;

import com.github.kdm1jkm.enigma.MachineBody;
import com.github.kdm1jkm.enigma.PlugBoard;
import com.github.kdm1jkm.enigma.Reflector;
import com.github.kdm1jkm.enigma.Rotator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.github.kdm1jkm.enigma.Constants.getShuffledAlphabets;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        MachineBody body;

        switch (args[0]) {
            case "M":
            case "Manual": {
                String filename = args[1];
                String plugBoard;
                String reflector;
                List<String> rotators;
                String rotatorSorts;
                String rotatorInit;
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
                body = getMachineBody(plugBoard, reflector, rotators, rotatorSorts, rotatorInit);
                break;
            }
            case "E":
            case "Example": {
                String plugBoard;
                String reflector;
                List<String> rotators;
                String rotatorSorts;
                String rotatorInit;
                plugBoard = PlugBoard.EXAMPLE;
                reflector = Reflector.EXAMPLE;
                rotators = Rotator.ROTATOR_EXAMPLES;

                rotatorSorts = args[1];
                rotatorInit = args[2];
                body = getMachineBody(plugBoard, reflector, rotators, rotatorSorts, rotatorInit);
                break;
            }
            case "C":
            case "Create": {
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(args[1]))) {
                    Random random = new Random();

                    // PlugBoard
                    // from 0 to 13
                    int plugBoardNum = random.nextInt(14);
                    writer.print(getShuffledAlphabets().substring(0, plugBoardNum * 2));
                    writer.print('\n');

                    // Reflector
                    writer.print(getShuffledAlphabets());
                    writer.print('\n');

                    //Rotator
                    int rotatorNum = Integer.parseInt(args[2]);
                    for (int i = 0; i < rotatorNum; i++) {
                        writer.print(getShuffledAlphabets());
                        writer.print('\n');
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Can't make file.");
                    System.exit(0);
                    return;
                }
                System.exit(0);
                return;
            }

            default: {
                printInvalid();
                return;
            }
        }

        if (body == null) {
            System.exit(0);
            return;
        }

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

    private static MachineBody getMachineBody(String plugBoard, String reflector, List<String> rotators, String rotatorSorts, String rotatorInit) {
        if (rotatorSorts.length() != rotatorInit.length()) {
            printInvalid();
            System.exit(0);
            return null;
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

        return new MachineBody(plugBoard, reflector, selectedRotators, selectedRotatorSettings);
    }

    private static void printInvalid() {
        System.out.println("Invalid input");
    }

    private static void printHelp() {
        System.out.println("-----Usage-----");
        System.out.println("1. java -jar EnigmaMachine.jar E[xample] filename) rotators... rotatorsInit...");
        System.out.println("2. java -jar EnigmaMachine.jar M[anual] filename rotators... rotatorsInit...");
        System.out.println("3. java -jar EnigmaMachine.jar C[reate] filename rotatorNumber");
        System.out.println("Ex) java -jar EnigmaMachine.jar E 312 WND");
        System.out.println("Ex) java -jar EnigmaMachine.jar M setting.txt 1523 ANGS");
        System.out.println("Ex) java -jar EnigmaMachine.jar C machine-example.txt 5");
        System.out.println("\n-----File Format---");
        System.out.println("plugboard(AGBDHT -> A-G | B-D | H-T)");
        System.out.println("reflector");
        System.out.println("rotators...");
    }
}
