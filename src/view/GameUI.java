package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Choice;

public class GameUI extends Application implements GameView{
    private final GameController gameController = new GameController(this);
    private Text resultText;
    private Text scoreText;

    @Override
    public void start(Stage stage){
        // set up the stage
        stage.setTitle("Rock Paper Scissors");

        // create a VBox layout
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // create result and score text
        resultText = new Text("Choose Rock, Paper, or Scissors");
        scoreText = new Text("Score - You: 0 Computer: 0");

        // create buttons for user choices
        Button rockButton = new Button("Rock");
        Button paperButton = new Button("Paper");
        Button scissorsButton = new Button("Scissors");
        Button exitButton = new Button("Exit");

        // set up event handlers
        rockButton.setOnAction(_ -> handleChoice("rock"));
        paperButton.setOnAction(_ -> handleChoice("paper"));
        scissorsButton.setOnAction(_ -> handleChoice("scissors"));
        exitButton.setOnAction(_ -> stage.close());

        // add buttons and texts to VBox
        vbox.getChildren().addAll(rockButton, paperButton, scissorsButton, resultText, scoreText, exitButton);

        //set the scene and show the stage
        Scene scene = new Scene(vbox, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showWelcomeMessage() {
        resultText.setText("Welcome to Rock, Paper, Scissors!");
    }

    @Override
    public void showUserFirstMessage() {
        resultText.setText("You are going first!");
    }

    @Override
    public void showComputerFirstMessage() {
        resultText.setText("Computer is going first!");
    }

    @Override
    public void showRoundMessage(int round) {
        resultText.setText("Round " + round);
    }

    @Override
    public void showChoicePrompt() {
        resultText.setText("Enter your choice (rock, paper, scissors): ");
    }

    @Override
    public void showChoices(Choice userChoice, Choice computerChoice) {
        resultText.setText("You chose " + userChoice.getChoice() + "\nComputer chose " + computerChoice.getChoice());
    }

    @Override
    public void showResult(String result) {
        resultText.setText(result);
    }

    @Override
    public void showInvalidChoiceMessage() {
        resultText.setText("Invalid choice! Please enter rock, paper, or scissors.");
    }

    @Override
    public void showFinalScore(int userScore, int computerScore) {
        scoreText.setText("Final Score: You - " + userScore + " Computer - " + computerScore);
    }

    @Override
    public void showGameWinner(String message) {
        resultText.setText(message);
    }

    // Method to handle user choices in GameUI
    private void handleChoice(String userChoice) {
        String result = gameController.processRound(userChoice);
        resultText.setText(result);
        scoreText.setText("Score - You: " + gameController.getUserScore() + " Computer: " + gameController.getComputerScore());
    }

    public static void main(String[] args) {
        launch(args);
    }
}