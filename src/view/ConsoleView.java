package view;

import model.Choice;

public class ConsoleView {
    public static void showWelcomeMessage(){
        System.out.println("Welcome to Rock, Paper, Scissors!");
    }

    public static void showUserFirstMessage(){
        System.out.println("You are going first!");
    }

    public static void showComputerFirstMessage(){
        System.out.println("Computer is going first!");
    }

    public static void showRoundMessage(int round){
        System.out.println("\nRound " + round);
    }

    public static void showChoicePrompt(){
        System.out.println("Enter your choice (rock, paper, scissors): ");
    }

    public static void showChoices(Choice userChoice, Choice computerChoice){
        System.out.println("You chose " + userChoice.getChoice());
        System.out.println("Computer chose " + computerChoice.getChoice());
    }

    public static void showResult(String result){
        System.out.println(result);
    }

    public static void showInvalidChoiceMessage(){
        System.out.println("Invalid choice! Please enter rock, paper, or scissors.");
    }

    public static void showFinalScore(int userScore, int computerScore){
        System.out.println("\nFinal Score:");
        System.out.println("You: " + userScore);
        System.out.println("Computer: " + computerScore);
    }

    public static void showGameWinner(String message){
        System.out.println(message);
    }
}
