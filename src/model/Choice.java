package model;

public abstract class Choice {
    // Abstract method to be implemented by subclasses
    public abstract String getChoice();

    // Method to determine the winner
    public String determineWinner(Choice opponent){
        if(this.getChoice().equals(opponent.getChoice())){
            return "It's a tie!";
        } else if(
                (this instanceof Rock && opponent instanceof Scissors) ||
                (this instanceof Paper && opponent instanceof Rock) ||
                (this instanceof Scissors && opponent instanceof Paper)
        ){
            return "You win!";
        } else {
            return "You lose!";
        }
    }
}
