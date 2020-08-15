package pl.coderslab.taskmanager;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    private static String[][] taskList = new String[0][];

    public static void main(String[] args) {
        TaskManager.run();
    }

    public static void run() {

        showWelcomeMessage();
        loadTaskListFromFile();
        while (true) {
            showMainMenu();
            String userChoice = getUserChoice();
            if (validateUserChoice(userChoice)) {
                executeValidChoice(userChoice);
                if (isExitChoice(userChoice)) {
                    break;
                }
            } else {
                executeInvalidChoice(userChoice);
            }
        }
        showExitMessage();
        saveTaskListToFile();
    }
    private static void saveTaskListToFile() {

    }

    private static void loadTaskListFromFile() {

    }

    private static String getUserChoice() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine().trim();
        //return System.console().readLine(); do odczytania pojedńczej lini tekstu
    }

    private static boolean validateUserChoice(String userChoice) {
    String[] validChoices = {"add", "remove", "list", "exit"};
    Arrays.sort(validChoices);
    int index = Arrays.binarySearch(validChoices, userChoice);
    if (index >=0) {
        return true;
    } else {
        return false;
        }
    }

    private static void executeValidChoice(String userChoice) {
        switch (userChoice) {
            case "add" : executeAddChoice();
                break;
            case "remove" : executeRemoveChoice();
                break;
            case "list" : executeListChoice();
                break;

        }
    }

    private static void executeRemoveChoice() {
    }

    private static void executeListChoice() {
    }

    private static void executeAddChoice() {
    }

    private static boolean isExitChoice(String userChoice) {
    return "exit".equalsIgnoreCase(userChoice);
    }

    private static void executeInvalidChoice(String userChoice) {
        System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid option " + userChoice +
                " Please choose a valid option");
        System.out.println(ConsoleColors.RESET);
    }

    private static void showMainMenu() {
        System.out.println(ConsoleColors.BLUE + "Please select an option");
        System.out.println(ConsoleColors.RESET);
        System.out.println("\tAdd");
        System.out.println("\tRemove");
        System.out.println("\tList");
        System.out.println("\tExit");
    }

    private static void showExitMessage() {
        System.out.println(ConsoleColors.RED + "Goodbye and remember to be back soon");
        System.out.println(ConsoleColors.RESET);
    }

    private static void showWelcomeMessage() {
        System.out.println(ConsoleColors.RED + "Welcome in Task Manager");
        System.out.println(ConsoleColors.RESET);
    }
}