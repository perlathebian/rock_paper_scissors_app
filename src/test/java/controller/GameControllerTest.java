package controller;

import model.Choice;
import model.Rock;
import model.Paper;
import model.Scissors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.GameView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameView mockView;
    private GameController gameController;

    @BeforeEach
    void setUp() {
        mockView = mock(GameView.class);
        gameController = new GameController(mockView);
    }

    @Test
    void testGetRoundsPlayed() {
        // Verify that the initial round count is 0
        assertEquals(0, gameController.getRoundsPlayed());

        // Simulate some rounds played
        gameController.processRound("rock");
        gameController.processRound("paper");

        // Verify the rounds played count
        assertEquals(2, gameController.getRoundsPlayed());
    }

    @Test
    void testGetUserScore() {
        // Initial score should be 0
        assertEquals(0, gameController.getUserScore());

        // Simulate rounds and score updates
        gameController.processRound("rock"); // assume this is a win
        gameController.processRound("scissors"); // assume this is a loss

        // Verify that score updates correctly
        assertEquals(1, gameController.getUserScore()); // 1 win
    }

    @Test
    void testGetComputerScore() {
        // Initial score should be 0
        assertEquals(0, gameController.getComputerScore());

        // Simulate rounds and score updates
        gameController.processRound("rock"); // assume this is a win for user
        gameController.processRound("scissors"); // assume this is a loss for user

        // Verify that the computer's score updates
        assertEquals(1, gameController.getComputerScore()); // 1 loss for user means 1 win for computer
    }

    @Test
    void testProcessRound_UserWins() {
        // Simulate a win for the user by choosing "rock" and assuming computer chooses "scissors"
        String result = gameController.processRound("rock");

        // Verify round result
        assertTrue(result.contains("You win!"));
        assertTrue(result.contains("You chose: rock"));
        assertTrue(result.contains("Computer chose: scissors"));
    }

    @Test
    void testProcessRound_ComputerWins() {
        // Simulate a loss for the user by choosing "rock" and assuming computer chooses "paper"
        String result = gameController.processRound("rock");

        // Verify round result
        assertTrue(result.contains("You lose!"));
        assertTrue(result.contains("You chose: rock"));
        assertTrue(result.contains("Computer chose: paper"));
    }

    @Test
    void testProcessRound_Draw() {
        // Simulate a draw by both choosing "rock"
        String result = gameController.processRound("rock");

        // Verify round result
        assertTrue(result.contains("It's a tie!"));
        assertTrue(result.contains("You chose: rock"));
        assertTrue(result.contains("Computer chose: rock"));
    }

    @Test
    void testProcessRound_GameOver() {
        // Simulate a game over condition by playing 3 rounds
        gameController.processRound("rock"); // First round
        gameController.processRound("paper"); // Second round
        gameController.processRound("scissors"); // Third round

        // Check if the game over message is shown
        String finalMessage = gameController.processRound("rock"); // Should return the final game over message
        assertTrue(finalMessage.contains("Game Over! Final Score:"));
    }

    @Test
    void testCreateChoice_Rock() {
        Choice choice = gameController.createChoice("rock");
        assertTrue(choice instanceof Rock);
    }

    @Test
    void testCreateChoice_Paper() {
        Choice choice = gameController.createChoice("paper");
        assertTrue(choice instanceof Paper);
    }

    @Test
    void testCreateChoice_Scissors() {
        Choice choice = gameController.createChoice("scissors");
        assertTrue(choice instanceof Scissors);
    }

    @Test
    void testCreateChoice_InvalidChoice() {
        Choice choice = gameController.createChoice("invalid");
        assertNull(choice);  // invalid input should return null
    }

}
