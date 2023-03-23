package edu.eci.arsw.wordle.persistence;

public class PalabrasNotFoundException extends Exception{

    public PalabrasNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
