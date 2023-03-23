package edu.eci.arsw.wordle.model;

public class Palabra {
    private String text;
    private boolean taken = false;

    public Palabra(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
