package view;

import controller.GameController;
import model.Choice;

public class ConsoleView implements GameView {
    @Override
    public void showWelcomeMessage() {
        System.out.println("Welcome to Rock, Paper, Scissors!");
    }

    @Override
    public void showUserFirstMessage() {
        System.out.println("You are going first!");
    }

    @Override
    public void showComputerFirstMessage() {
        System.out.println("Computer is going first!");
    }

    @Override
    public void showRoundMessage(int round) {
        System.out.println("\nRound " + round);
    }

    @Override
    public void showChoicePrompt() {
        System.out.println("Enter your choice (rock, paper, scissors): ");
    }

    @Override
    public void showChoices(Choice userChoice, Choice computerChoice) {
        System.out.println("You chose " + userChoice.getChoice());
        System.out.println("Computer chose " + computerChoice.getChoice());
    }

    @Override
    public void showResult(String result) {
        System.out.println(result);
    }

    @Override
    public void showInvalidChoiceMessage() {
        System.out.println("Invalid choice! Please enter rock, paper, or scissors.");
    }

    @Override
    public void showFinalScore(int userScore, int computerScore) {
        System.out.println("\nFinal Score:");
        System.out.println("You: " + userScore);
        System.out.println("Computer: " + computerScore);
    }

    @Override
    public void showGameWinner(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();
        GameController gameController = new GameController(consoleView);
        gameController.startGame();
    }
}
