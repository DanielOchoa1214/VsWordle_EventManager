package edu.eci.arsw.wordle.persistence;

public class LobbyException extends Exception{

    public static String IS_CLOSED = "El lobby ya inicio";
    public static String PLAYER_EXISTS = "El jugador ya existe";
    public static String IS_NOT_FINISHED = "El lobby no ha terminado de jugar";
    public static String LOBBY_NOT_FOUND = "El lobby no se encontro";

    public static String EMPTY_NICK = "Tu nickname no puede estar vacio :(";

    public LobbyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
