package controller;

import model.Choice;
import model.Rock;
import model.Paper;
import model.Scissors;
import view.ConsoleView;
import view.GameView;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    private int userScore = 0;
    private int computerScore = 0;
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private final GameView view;
    private int roundsPlayed = 0;

    public GameController(GameView view){
        this.view = view;
    }

    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    public String processRound(String userChoice){
        // if game is already over, return final message immediatelu
        if(roundsPlayed >= 3) {
            return "Game Over! Final Score: You: " + userScore + " Computer: " + computerScore;
        }

        // for console mode, if no choice was provided, get input from user
        Choice userChoiceObj;
        if(userChoice == null){
            userChoiceObj = getUserChoice();
        } else {
            userChoiceObj = createChoice(userChoice);
        }

        // get computer's choice
        Choice computerChoiceObj = getComputerChoice();

        // determine round result and update score
        String result = userChoiceObj.determineWinner(computerChoiceObj);
        updateScore(result);
        roundsPlayed++;

        // if this was the final (3rd) round, show final score and winner via view
        if(roundsPlayed >= 3){
            if(userScore > computerScore){
                view.showGameWinner("You win the game!");
            } else if(computerScore > userScore){
                view.showGameWinner("You lose the game!");
            } else {
                view.showGameWinner("It's a tie!");
            }
            String finalMessage = "Game Over! Final Score: You: " + userScore + " Computer: " + computerScore;
            view.showFinalScore(userScore, computerScore);
            return finalMessage;
        }

        // otherwise, return the round result
        return "You chose: " + userChoiceObj.getChoice() +
                "\nComputer chose: " + computerChoiceObj.getChoice() +
                "\n" + result;
    }

    // for console mode
    private Choice getUserChoice(){
        Choice userChoice = null;
        while(userChoice == null){
            view.showChoicePrompt();
            String userInput = scanner.nextLine().toLowerCase();
            userChoice = createChoice(userInput);
            if(userChoice == null){
                view.showInvalidChoiceMessage();
            }
        }
        return userChoice;
    }

    // for both console and gui modes
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

    // Getter methods for the scores
    public int getUserScore() {
        return userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }
}
