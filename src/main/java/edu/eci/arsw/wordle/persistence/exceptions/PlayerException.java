package edu.eci.arsw.wordle.persistence.exceptions;

public class PlayerException extends Exception{

    public static String NOT_FOUND_PLAYER = "No se encontro/encontraron los jugadores.... :(";
    public PlayerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
