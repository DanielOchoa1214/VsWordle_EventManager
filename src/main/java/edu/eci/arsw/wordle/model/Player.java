package edu.eci.arsw.wordle.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        return this.equals((Player) obj);
    }

    private boolean equals(Player player){
        return this.nickname.equals(player.nickname);
    }
}
