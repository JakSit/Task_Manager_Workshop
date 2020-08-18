package pl.coderslab.taskmanager;

import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
        try {
            Path tasks = Paths.get("tasks.csv");
            String[] task = new String[taskList.length];
            for (int i = 0; i < task.length; i++){
                for (int j = 0; j < task.length; j++){
                    task[i] = taskList[i][j];
                }
            }
            Files.write(tasks, Arrays.asList(task));
    } catch (IOException e) {
        e.printStackTrace();
        }
    }

    private static void loadTaskListFromFile() {
        Path tasks = Paths.get("tasks.csv");
        if (!Files.exists(tasks)) {
            System.out.print("File not exist!");
            System.exit(0);
        }
        String [][] tab = null;
        try {
            List<String> strings = Files.readAllLines(tasks);
            tab = new String[strings.size()][strings.get(0).split(",").length];
            for (int i = 0; i < taskList.length; i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < taskList.length; j++) {
                    tab[i][j] = split[j];
                    taskList[i][j] = tab[i][j];
                }
            }
            System.out.println(Arrays.toString(taskList));
        } catch (IOException e) {
        e.printStackTrace();
        }
    }


    private static String getUserChoice() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine().trim();
        //return System.console().readLine(); do odczytania pojedyńczej linii tekstu
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please select number to remove: ");
        if (!scanner.hasNextInt()) {
            System.out.print("Please type correct answer: ");
        }
        int number = scanner.nextInt();
        if (taskList[number] == null) {
            System.out.println("Error due to removing value");
        } else {
            ArrayUtils.remove(taskList, number +1);
            System.out.println("Value has been sucessfully removed");
        }
    }

    private static void executeListChoice() {
        System.out.println(Arrays.toString(taskList));
    }

    private static void executeAddChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description:");
        String desc = scanner.nextLine();
        System.out.println("Please add task due date: ");
        String date = scanner.nextLine();
        System.out.println("Is Your task important: true/false ");
        String important = scanner.nextLine();
        Arrays.copyOf(taskList, taskList.length + 1);
        taskList[taskList.length-1] = new String[3];
        taskList[taskList.length-1][0] = desc;
        taskList[taskList.length-1][1] = date;
        taskList[taskList.length-1][2] = important;
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