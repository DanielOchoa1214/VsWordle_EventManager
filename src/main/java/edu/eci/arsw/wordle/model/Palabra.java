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

    @Override
    public boolean equals(Object obj) {
        return this.equals((Palabra) obj);
    }

    private boolean equals(Palabra obj){
        return this.text.equals(obj.getText());
    }

    @Override
    public String toString() {
        return this.text;
    }
}
