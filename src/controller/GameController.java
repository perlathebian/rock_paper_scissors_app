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

    public void startGame(){
        view.showWelcomeMessage();

        int starter = random.nextInt(2);
        if(starter == 0){
            view.showUserFirstMessage();
        } else {
            view.showComputerFirstMessage();
        }

        for(int round = 1; round <= 3; round++){
            view.showRoundMessage(round);
            Choice userChoice = getUserChoice();
            Choice computerChoice = getComputerChoice();

            view.showChoices(userChoice, computerChoice);

            String result = userChoice.determineWinner(computerChoice);
            view.showResult(result);

            updateScore(result);
            roundsPlayed++;
            if (roundsPlayed == 3) {
                showFinalScore();
                break;
            }
        }
    }

    public String playRound(String userChoice) {
        if (roundsPlayed >= 3) {
            return "Game Over! Final Score: You: " + userScore + " Computer: " + computerScore;
        }

        Choice userChoiceObj = createChoice(userChoice);
        Choice computerChoiceObj = getComputerChoice();

        String result = userChoiceObj.determineWinner(computerChoiceObj);
        updateScore(result);
        roundsPlayed++;
        return "You chose: " + userChoice + "\nComputer chose: " + computerChoiceObj.getChoice() + "\n" + result;
    }

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
        view.showFinalScore(userScore, computerScore);
        if(userScore > computerScore){
            view.showGameWinner("You win the game!");
        } else if(computerScore > userScore){
            view.showGameWinner("You lose the game!");
        } else {
            view.showGameWinner("It's a tie!");
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
