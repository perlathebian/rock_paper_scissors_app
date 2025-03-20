package view;

import model.Choice;

public interface GameView {
    void showWelcomeMessage();
    void showUserFirstMessage();
    void showComputerFirstMessage();
    void showRoundMessage(int round);
    void showChoicePrompt();
    void showChoices(Choice userChoice, Choice computerChoice);
    void showResult(String result);
    void showInvalidChoiceMessage();
    void showFinalScore(int userScore, int computerScore);
    void showGameWinner(String message);
}
