package view;

import controller.GameController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#87CEFA")),   // Light Sky Blue
                new Stop(1, Color.web("#4682B4")));  // Steel Blue
        vbox.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));


        // create result and score text
        resultText = new Text("Choose Rock, Paper, or Scissors");
        scoreText = new Text("Score - You: 0 Computer: 0");
        resultText.setFont(Font.font("Verdana", 20));
        scoreText.setFont(Font.font("Verdana", 16));
        resultText.setFill(Color.WHITE);
        scoreText.setFill(Color.WHITE);

        // create buttons for user choices
        Button rockButton = createImageButton("rock");
        Button paperButton = createImageButton("paper");
        Button scissorsButton = createImageButton("scissors");
        Button exitButton = new Button("Exit");

        exitButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 10;");
        exitButton.setPrefWidth(100);
        exitButton.setPrefHeight(40);

        // set up event handlers
        rockButton.setOnAction(event -> handleChoice("rock"));
        paperButton.setOnAction(event -> handleChoice("paper"));
        scissorsButton.setOnAction(event -> handleChoice("scissors"));
        exitButton.setOnAction(event -> stage.close());

        // add buttons and texts to VBox
        vbox.getChildren().addAll(rockButton, paperButton, scissorsButton, resultText, scoreText, exitButton);

        //set the scene and show the stage
        Scene scene = new Scene(vbox, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    private Button createImageButton(String choice){
        Image image = new Image(getClass().getResourceAsStream("/resources/" + choice + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent;");
        button.setPrefSize(80, 80);
        return button;
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