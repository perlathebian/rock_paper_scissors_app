package app;

import controller.GameController;
import javafx.application.Application;
import view.ConsoleView;
import view.GameUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rock Paper Scissors!");
        System.out.println("Please select a mode:");
        System.out.println("1. Console Mode");
        System.out.println("2. GUI Mode");

        int choice = scanner.nextInt();

        if (choice == 1) {
            // Console Mode
            GameController consoleController = new GameController(new ConsoleView());
            consoleController.startGame();
        } else if (choice == 2) {
            // GUI Mode
            Application.launch(GameUI.class, args);
        } else {
            System.out.println("Invalid choice. Please select either 1 or 2.");
        }
    }
}
