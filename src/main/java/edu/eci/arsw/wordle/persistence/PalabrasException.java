package edu.eci.arsw.wordle.persistence;

public class PalabrasException extends Exception{

    public static String NOT_FOUND_PALABRA = "No se encontro/encontraron las palabras.... :(";
    public PalabrasException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
