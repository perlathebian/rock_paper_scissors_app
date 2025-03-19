package controller;

import model.Choice;
import model.Rock;
import model.Paper;
import model.Scissors;
import view.ConsoleView;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    private int userScore = 0;
    private int computerScore = 0;
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public void startGame(){
        ConsoleView.showWelcomeMessage();

        int starter = random.nextInt(2);
        if(starter == 0){
            ConsoleView.showUserFirstMessage();
        } else {
            ConsoleView.showComputerFirstMessage();
        }

        for(int round = 1; round <= 3; round++){
            ConsoleView.showRoundMessage(round);
            Choice userChoice = getUserChoice();
            Choice computerChoice = getComputerChoice();

            ConsoleView.showChoices(userChoice, computerChoice);

            String result = userChoice.determineWinner(computerChoice);
            ConsoleView.showResult(result);

            updateScore(result);
        }
        showFinalScore();
    }

    private Choice getUserChoice(){
        Choice userChoice = null;
        while(userChoice == null){
            ConsoleView.showChoicePrompt();
            String userInput = scanner.nextLine().toLowerCase();
            userChoice = createChoice(userInput);
            if(userChoice == null){
                ConsoleView.showInvalidChoiceMessage();
            }
        }
        return userChoice;
    }

    private Choice getComputerChoice(){
        String[] options = {"rock", "paper", "scissors"};
        String computerInput = options[random.nextInt(3)];
        return createChoice(computerInput);
    }

    private Choice createChoice(String choice){
        return switch (choice) {
          case "rock" -> new Rock();
          case "paper" -> new Paper();
          case "scissors" -> new Scissors();
          default -> null;
        };
    }

    private void updateScore(String result){
        if(result.equals("You win!")){
            userScore++;
        } else if(result.equals("You lose!")){
            computerScore++;
        }
    }

    private void showFinalScore(){
        ConsoleView.showFinalScore(userScore, computerScore);
        if(userScore > computerScore){
            ConsoleView.showGameWinner("You win the game!");
        } else if(computerScore > userScore){
            ConsoleView.showGameWinner("You lose the game!");
        } else {
            ConsoleView.showGameWinner("It's a tie!");
        }
    }
}
