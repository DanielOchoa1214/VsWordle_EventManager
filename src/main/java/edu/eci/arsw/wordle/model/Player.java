package edu.eci.arsw.wordle.model;

public class Player {
    private String nickname;
    private int roundsWon = 0;

    public Player(String nickname){
        this.nickname = nickname;
    }

    public Player(){}

    public void addRoundWon(){
        roundsWon++;
    }

    public String getNickname() {
        return nickname;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    @Override
    public String toString() {
        return "Nickname: " + nickname;
    }
}
