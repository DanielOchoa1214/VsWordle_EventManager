package edu.eci.arsw.wordle.persistence;

public class PlayerNotFoundException extends Exception{

    public PlayerNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
