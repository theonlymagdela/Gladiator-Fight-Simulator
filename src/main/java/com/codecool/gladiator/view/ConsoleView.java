package com.codecool.gladiator.view;

import java.util.Scanner;

/**
 * Basic console view implementation
 */
public class ConsoleView implements Viewable {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void display(String text) {
        System.out.println(text);
    }

    @Override
    public int getNumberBetween(int min, int max) {

        int userInput;
        boolean invalidInput;

        do {
            System.out.println("Enter a number between " + min + " and " + max + ":");
            userInput = scanner.nextInt();

            if (userInput < min || userInput > max) {
                System.out.println("Invalid!");
                invalidInput = true;
                continue;
            }
            invalidInput = false;
        } while (invalidInput);

        return userInput;
    }
}
