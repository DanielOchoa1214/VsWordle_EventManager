package edu.eci.arsw.wordle.model;

public class Player {
    private String nickname;
    private int roundsWon;

    public Player(String nickname, int roundsWon){
        this.nickname = nickname;
        this.roundsWon = roundsWon;
    }

    public void addRoundWon(){
        roundsWon++;
    }

    public String getNickname() {
        return nickname;
    }

    public int getRoundsWon() {
        return roundsWon;
    }
}
